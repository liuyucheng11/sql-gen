package com.itworkorder.sqlgen.common.tables;

import static com.itworkorder.sqlgen.common.tables.TableNameEnum.*;

/**
 * <p>表处理事件枚举</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2019-12-31  17:15
 * @version 1.0
 */
public enum TableSolution {

    UPDATE_SUPPLIER_AU(T_SUPPLIER, 1, "修改认证状态"),
    UPDATE_SUPPLIER_NAME(T_SUPPLIER, 3, "修改供应商名"),
    UPDATE_SUPPLIER_EFFECTIVE_DATE(T_SUPPLIER, 2, "修改供应商时间"),
    UPDATE_SUPPLIER_ALL_SUPPLIER_STORE(T_SUPPLIER, 4, "修改是否所有门店"),
    /**---------------------------------------------------------------------*/
    DELETE_SUPPLIER_BANK(T_SUPPLIER_BANK_RELATION,1,"删除供应商银行"),
    UPDATE_BANK_ACCOUNT(T_SUPPLIER_BANK_RELATION,2,"修改账号信息"),
    /**--------------------------------------------------------------*/
    UPDATE_CONTRACT_END_DATE(T_CONTRACT_DETAIL,1,"修改合同结束时间");
    private TableNameEnum tableNameEnum;

    private Integer type;

    private String desc;

    TableSolution(TableNameEnum tableNameEnum, Integer type, String desc) {
        this.tableNameEnum = tableNameEnum;
        this.type = type;
        this.desc = desc;
    }

    public TableNameEnum getTableNameEnum() {
        return tableNameEnum;
    }

    public void setTableNameEnum(TableNameEnum tableNameEnum) {
        this.tableNameEnum = tableNameEnum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
