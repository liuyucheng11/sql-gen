package com.itworkorder.sqlgen.controller;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;
import com.itworkorder.sqlgen.service.HandlerContext;
import com.itworkorder.sqlgen.service.impl.HandlerSqlGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:所有的Sql工单路由到generateSql处理
 * 根据 code type区分
 * @link com.itworkorder.sqlgen.pojo.supplier.BaseDTO
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2019-12-31  17:04
 * @version 1.0
 */
@Controller
@RequestMapping("/api")
public class SqlGenerateController {

    @Autowired
    HandlerSqlGenService handlerSqlGenService;

    @RequestMapping("/generateSql")
    @ResponseBody
    public Result generateSql(@RequestParam("json") String json) {

        return handlerSqlGenService.handler(json);
    }


}
