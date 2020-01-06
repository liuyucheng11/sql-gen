package com.itworkorder.sqlgen.service;


import com.itworkorder.sqlgen.common.tables.TableNameEnum;
import com.itworkorder.sqlgen.common.tables.TableSolution;
import com.itworkorder.sqlgen.util.BeanTool;

import java.util.Map;

/**
 * desc: <p>处理上下文</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3 15:20
 */
@SuppressWarnings("unchecked")
public class HandlerContext {

    private Map<TableNameEnum, Class> handlerMap;

    public HandlerContext(Map<TableNameEnum, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public BaseSqlGenService getInstance(TableNameEnum type) {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }
        return (BaseSqlGenService) BeanTool.getBean(clazz);
    }

}
