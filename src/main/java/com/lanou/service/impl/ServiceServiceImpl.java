package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    // 查询全部
    @Override
    public List<Service> findAllService(Service service) {

        return serviceMapper.findAllService(service);
    }

    // 添加
    @Override
    public Integer saveService(Service service) {

        return serviceMapper.insert(service);
    }

    // 分页
    @Override
    public PageInfo<Service> getPageinfo(Integer pageNo, Integer pageSize) {

        return queryCostByPage(pageNo, pageSize);
    }

    // 参数是要查询的表所对应的实体类
    public PageInfo<Service> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数合法性：默认页数1 默认每页大小5
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的资费信息，此时cost全为null
        Service service = new Service();
        List<Service> serviceList = serviceMapper.findAllService(service);

        // 使用PageInfo对结果进行包装
        // 参数：全部信息的集合
        PageInfo<Service> pageInfo = new PageInfo<Service>(serviceList);

        return pageInfo;
    }

}
