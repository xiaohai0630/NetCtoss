package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;

public interface CostMapper {
    // 通过主键删除
    int deleteByPrimaryKey(Integer costId);

    // 保存
    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    // 查询全部花费信息
    List<Cost> findAllCost();
}