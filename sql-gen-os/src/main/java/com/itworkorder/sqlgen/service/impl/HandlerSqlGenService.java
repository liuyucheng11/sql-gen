package com.itworkorder.sqlgen.service.impl;


import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  15:14
 * @version 1.0
 */
public interface HandlerSqlGenService {

    Result handler(String json);
}
