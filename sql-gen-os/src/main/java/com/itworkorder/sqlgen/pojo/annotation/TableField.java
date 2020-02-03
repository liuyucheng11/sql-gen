package com.itworkorder.sqlgen.pojo.annotation;

import java.lang.annotation.*;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-3  11:54
 * @version 1.0
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TableField {
    String value();
}
