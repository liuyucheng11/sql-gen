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
    private Integer code;

    /**
     * 工单下类型
     */
    private Integer type;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
