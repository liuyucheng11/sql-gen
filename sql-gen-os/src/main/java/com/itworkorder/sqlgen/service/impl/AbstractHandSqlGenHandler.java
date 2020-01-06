package com.itworkorder.sqlgen.service.impl;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.common.SqlContentTemplate;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;
import com.itworkorder.sqlgen.service.BaseSqlGenService;

import java.text.MessageFormat;

/**
 * <p>抽象工单处理基类</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  14:02
 * @version 1.0
 */
public abstract class AbstractHandSqlGenHandler<T extends BaseDTO> implements BaseSqlGenService<T> {

    private String bindTableName;

    private final static String BACK_UP_TABLE_PREFIX = "backup_tables.";

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
        StringBuffer buffer = new StringBuffer();
        setBackUpTableName(BACK_UP_TABLE_PREFIX + bindTableName + "_20192323");
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
