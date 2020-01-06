package com.itworkorder.sqlgen.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DataSourceAop {

    @Pointcut("(execution(* com.itworkorder.sqlgen.service.impl.*.select*(..)) " +
            "|| execution(* com.itworkorder.sqlgen.service.impl.*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut(
            "execution(* com.itworkorder.sqlgen.service.impl.*.insert*(..)) " +
                    "|| execution(* com.itworkorder.sqlgen.service.impl.*.add*(..)) " +
                    "|| execution(* com.itworkorder.sqlgen.service.impl.*.update*(..)) " +
                    "|| execution(* com.itworkorder.sqlgen.service.impl.*.edit*(..)) " +
                    "|| execution(* com.itworkorder.sqlgen.service.impl.*.delete*(..)) " +
                    "|| execution(* com.itworkorder.sqlgen.service.impl.*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}