package com.itworkorder.sqlgen.pojo.supplier;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  13:56
 * @version 1.0
 */
public class BaseDTO {
    /**
     * 对应工单处理大类 表名
     */
    private Integer sqlCode;

    /**
     * 工单下类型
     */
    private Integer sqlType;


    public Integer getSqlCode() {
        return sqlCode;
    }

    public void setSqlCode(Integer sqlCode) {
        this.sqlCode = sqlCode;
    }

    public Integer getSqlType() {
        return sqlType;
    }

    public void setSqlType(Integer sqlType) {
        this.sqlType = sqlType;
    }
}
