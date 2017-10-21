package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface CostService{

    // 查询全部的cost
    List<Cost> findAllCost();

    Integer saveCost(Cost cost);

    // 分页
    List<Cost> findWithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<Cost> getPageinfo(Integer pageSize);
}
