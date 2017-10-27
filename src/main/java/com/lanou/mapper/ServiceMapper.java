package com.lanou.mapper;

import com.lanou.bean.Service;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    // 添加
    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer serviceId);

    // 修改
    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    // 查询全部
    List<Service> findAllService(Service service);

    // 查询部分
    List<Service> findSomeService(Service service);
}