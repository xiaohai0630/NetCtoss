package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    // 查询全部的cost
    public List<Cost> findAllCost() {

        List<Cost> costList = costMapper.findAllCost();

        return costList;
    }


    // 分页
    // 目标：PageInfo对象
    public List<Cost> findWithPageInfo(Integer pageNo, Integer pageSize) {

        PageInfo<Cost> pageInfo = queryCostByPage(pageNo,pageSize);
        // 要查询的页面和这个页面的信息
        return pageInfo.getList();
    }

    // 获取分页信息－－为了做显示页面的部分
    public PageInfo<Cost> getPageinfo(Integer pageSize) {

        return queryCostByPage(null,pageSize);
    }

    // 参数是要查询的表所对应的实体类
    public PageInfo<Cost> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数合法性：默认页数1 默认每页大小2
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 2 : pageSize;


        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的学员信息
        List<Cost> costList = costMapper.findAllCost();

        // 使用PageInfo对结果进行包装
        // 参数：全部信息的集合
        PageInfo<Cost> costPageInfo = new PageInfo<Cost>(costList);

        return costPageInfo;
    }

}
