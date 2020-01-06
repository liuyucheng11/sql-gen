package com.itworkorder.sqlgen.common;

import java.io.Serializable;

/**
 * <p>结果返回类</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2019-12-31  17:08
 * @version 1.0
 */
public class Result<T> implements Serializable {

    private T re;
    private Integer code;
    private String msg;

    private static final Integer SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "success";
    private static final String BUSINESS_ERROR_MSG = "business error";

    private static final String FAIL_MSG = "error";

    private static final Integer BUSINESS_ERROR = -1;

    public Result(T re, Integer code, String msg) {
        this.re = re;
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public Result(T re) {
        this.re = re;
    }

    public T getRe() {
        return re;
    }

    public void setRe(T re) {
        this.re = re;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SuppressWarnings("unchecked")
    public static <T> Result getSuccessRe(T re) {
        Result result = new Result(re);
        result.setCode(0);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    public static Result getSuccessRe() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Result getBusinessErrorRe() {
        Result result = new Result(null);
        result.setCode(BUSINESS_ERROR);
        result.setMsg(BUSINESS_ERROR_MSG);
        return result;
    }
}
