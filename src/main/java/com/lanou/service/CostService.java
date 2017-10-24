package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface CostService{

    // 查询全部的cost
    List<Cost> findAllCost(Cost cost);

    // 添加cost
    Integer saveCost(Cost cost);

    // 删除cost
    Integer delCost(Integer id);

    // 分页
    // pageNo：页码；pageSize：页面大小
    List<Cost> findWithPageInfo(Integer pageNo, Integer pageSize);
    // 获取pageInfo
    PageInfo<Cost> getPageinfo(Integer pageSize);
}
