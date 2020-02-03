package com.itworkorder.sqlgen.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.common.tables.TableNameEnum;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;
import com.itworkorder.sqlgen.service.BaseSqlGenService;
import com.itworkorder.sqlgen.service.HandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itworkorder.sqlgen.common.SqlContentTemplate;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  15:18
 * @version 1.0
 */
@Service
public class HandlerSqlGenServiceImpl implements HandlerSqlGenService {

    @Autowired
    HandlerContext handlerContext;

    @Override
    @SuppressWarnings("unchecked")
    public Result handler(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        TableNameEnum tableNameEnum = SqlContentTemplate.tableCache.get(jsonObject.get("sqlCode"));
        BaseSqlGenService service = handlerContext.getInstance(tableNameEnum);
        return service.handGenSql(JSON.parseObject(json,tableNameEnum.getDto()));
    }
}
