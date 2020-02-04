package com.itworkorder.sqlgen.service.impl;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.common.SqlContentTemplate;
import com.itworkorder.sqlgen.common.enums.DatePatternEnum;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;
import com.itworkorder.sqlgen.service.BaseSqlGenService;
import com.itworkorder.sqlgen.util.RedisUtil;
import com.itworkorder.sqlgen.util.SafeDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Date;

/**
 * <p>抽象工单处理基类</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  14:02
 * @version 1.0
 */
@Slf4j
public abstract class AbstractHandSqlGenHandler<T extends BaseDTO> implements BaseSqlGenService<T> {

    private String bindTableName = "";

    private final static String BACK_UP_TABLE_PREFIX = "backup_tables.";
    //工单日期
    private final String REDIS_DATE = "redis_date";
    //日期下第k个处理工单
    private final String K_COUNT = "k_count";
    /**
     * 备份表名
     */
    private String backUpTableName;

    protected ThreadLocal<StringBuffer> bufferLocal = new ThreadLocal<>();

    @Override
    public Result handGenSql(T dto) {
        this.beforeExecute();
        Result result = genSqlInvoke(dto);
        this.afterExecute();
        return result;
    }

    /**
     * 实际生成方法,各个类下重写
     *
     * @return
     */
    protected abstract Result genSqlInvoke(T dto);

    /**
     * desc: <p></p>
     * todo:利用redis控制生成的工单备份号
     *
     * @author yucheng.liu@ucarinc.com
     * Date: 2020-1-4 13:50
     */
    protected void beforeExecute() {
        RedisUtil redis = RedisUtil.getRedis();
        long now = Long.parseLong(SafeDateUtil.format(new Date(), DatePatternEnum.YYYYMMDD));
        String dateKey = bindTableName + REDIS_DATE;
        String numKey = bindTableName + K_COUNT;
        if (!StringUtils.isEmpty((String) redis.get(dateKey))) {
            if (now - Long.parseLong((String) redis.get(dateKey)) > 0) {
                redis.set(dateKey, SafeDateUtil.format(new Date(), DatePatternEnum.YYYYMMDD));
                redis.set(numKey, 1L);
            } else {
                redis.incr(numKey, 1);
            }
        } else {
            redis.set(dateKey, SafeDateUtil.format(new Date(), DatePatternEnum.YYYYMMDD));
            redis.expire(dateKey, 24 * 3600);
            redis.set(numKey, 1L);
        }
        String prefix = "_" + redis.get(dateKey) + "_" + redis.get(numKey);
        StringBuffer buffer = new StringBuffer();
        setBackUpTableName(BACK_UP_TABLE_PREFIX + bindTableName + prefix);
        buffer.append(MessageFormat.format(SqlContentTemplate.CREATE_BACKUP_TABLES, getBackUpTableName(), bindTableName)).append("; \n");
        bufferLocal.set(buffer);
    }

    protected void afterExecute() {
        bufferLocal.remove();
    }


    public String getBindTableName() {
        return bindTableName;
    }

    public void setBindTableName(String bindTableName) {
        this.bindTableName = bindTableName;
    }

    public ThreadLocal<StringBuffer> getBufferLocal() {
        return bufferLocal;
    }

    public void setBufferLocal(ThreadLocal<StringBuffer> bufferLocal) {
        this.bufferLocal = bufferLocal;
    }

    protected StringBuffer getBuffer() {
        return this.bufferLocal.get();
    }

    public String getBackUpTableName() {
        return backUpTableName;
    }

    public void setBackUpTableName(String backUpTableName) {
        this.backUpTableName = backUpTableName;
    }
}
