package com.itworkorder.sqlgen.service;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;

/**
 * <p>
 * 数据处理基础接口
 * </p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  13:52
 * @version 1.0
 */
public interface BaseSqlGenService<T extends BaseDTO> {

    Result handGenSql(T dto);

}
