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

    // 查询全部的cost，按照id查询cost
    @Override
    public List<Cost> findAllCost(Cost cost) {

        return costMapper.findAllCost(cost);
    }

    // 保存
    @Override
    public Integer saveCost(Cost cost) {

        return costMapper.insert(cost);
    }

    // 删除
    @Override
    public Integer delCost(Integer id) {

        return costMapper.deleteByPrimaryKey(id);
    }

    // 修改
    @Override
    public Integer changeCost(Cost cost) {
        // 修改成功返回1
        return costMapper.updateByPrimaryKeySelective(cost);
    }

    // 获取分页信息－－为了做显示页面的部分
    @Override
    public PageInfo<Cost> getPageinfo(Integer pageNo, Integer pageSize) {

        return queryCostByPage(pageNo, pageSize);
    }

    // 参数是要查询的表所对应的实体类
    public PageInfo<Cost> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数合法性：默认页数1 默认每页大小5
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的资费信息，此时cost全为null
        Cost cost = new Cost();
        List<Cost> costList = costMapper.findAllCost(cost);

        // 使用PageInfo对结果进行包装
        // 参数：全部信息的集合
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

        return pageInfo;
    }

}
