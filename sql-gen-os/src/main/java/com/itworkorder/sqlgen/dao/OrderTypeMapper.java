package com.itworkorder.sqlgen.dao;

import com.itworkorder.sqlgen.pojo.OrderType;

public interface OrderTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderType record);

    int insertSelective(OrderType record);

    OrderType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderType record);

    int updateByPrimaryKey(OrderType record);
}