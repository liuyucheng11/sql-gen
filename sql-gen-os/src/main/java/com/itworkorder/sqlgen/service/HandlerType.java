package com.itworkorder.sqlgen.service;

import com.itworkorder.sqlgen.common.tables.TableNameEnum;
import com.itworkorder.sqlgen.common.tables.TableSolution;

import java.lang.annotation.*;


/**
 * <p>自定义注解Spring扫描被该注解修饰类注册bean</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  14:22
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {

    TableNameEnum table();
}
