package com.itworkorder.sqlgen.common;

import com.google.common.collect.Maps;
import com.itworkorder.sqlgen.common.tables.TableNameEnum;
import com.itworkorder.sqlgen.common.tables.TableSolution;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>sql模板常量类</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2019-12-31  16:39
 * @version 1.0
 */
@Component
public class SqlContentTemplate {

    public static final Map<Integer, TableNameEnum> tableCache = Maps.newHashMap();

    public static final Map<Integer, TableSolution> soluteCache = Maps.newHashMap();


    static {
        for (TableNameEnum tEnum : TableNameEnum.values()) {
            tableCache.put(tEnum.getCode(), tEnum);
        }
        for (TableSolution solution : TableSolution.values()) {
            soluteCache.put(solution.getType(), solution);
        }
    }

    /**
     * 创建备份表
     */
    public static final String CREATE_BACKUP_TABLES = " create table {0} like {1}";

    /**
     * 根据单个字段删除数据基本sql
     */
    public static final String DELETE_DATA_BY_FIELD = "delete from {0} where {1} = {2} ";

    /**
     * 基础修改单个字段
     */
    public static final String UPDATE_SIMPLE_FIELD = "update {0} set {1} = {2} where {3} = {4} ";

    /**
     * 基础插入备份数据
     */
    public static final String INSERT_BASE_UP_DATA = "insert into {0} where select * from {1} where id = {2}";
    /**
     * 插入备份数据
     */
    public static final String INSERT_BACKUP_DATA = "insert into {0} where select * from {1} where id = select id from {2} where {3} = {4} ";
    /**
     * 修改供应商认证状态状态
     */
    public static final String UPDATE_SUPPLIER_AUTH = "update t_contract_supplier_details set authentication_status = {0} where select id from t_contract_supplier_details where {1} = {2} ";

    public static final String UPDATE_SUPPLIER_NAME = "update t_contract_supplier_details set supplier_name = {0} where select id from t_contract_supplier_details where {1} = {2} ";

    public static final String UPDATE_SUPPLIER_EFFECTIVE_DATE = "update t_contract_supplier_details set effe_time = {0} where supplier_no = {1}";


}
