package com.itworkorder.sqlgen.service;

import com.google.common.collect.Maps;
import com.itworkorder.sqlgen.common.tables.TableNameEnum;
import com.itworkorder.sqlgen.common.tables.TableSolution;
import com.itworkorder.sqlgen.util.ClassScaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @Author: CipherCui
 * @Description:
 * @Date: Created in 14:40 2019/2/1
 */
@Component
@SuppressWarnings("unchecked")
public class HandlerProcessor implements BeanFactoryPostProcessor {

    private static final String HANDLER_PACKAGE = "com.itworkorder.sqlgen.service.impl";

    /**
     * 扫描@HandlerType，初始化HandlerContext，将其注册到spring容器
     *
     * @param beanFactory bean工厂
     * @see HandlerType
     * @see HandlerContext
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<TableNameEnum, Class> handlerMap = Maps.newHashMap();
    /*    ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class).forEach(clazz -> {
            TableSolution tableSolution = clazz.getAnnotation(HandlerType.class).solute();
            handlerMap.put(tableSolution, clazz);
        });*/

        Set<Class<?>> set = ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class);
        for (Class<?> clazz : set) {
            TableNameEnum table = clazz.getAnnotation(HandlerType.class).table();
            handlerMap.put(table, clazz);
            try {
                beanFactory.registerSingleton(clazz.getName(),clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        HandlerContext context = new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }

}
