package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface ServiceService {
    // 查询全部
    List<Service> findAllService(Service service);

    // 查询部分
    List<Service> findSomeService(Service service);

    // 添加
    Integer saveService(Service service);

    // 修改状态
    Integer changeService(Service service);

    // 分页
    PageInfo<Service> getPageinfo(Integer pageNo, Integer pageSize);
}
