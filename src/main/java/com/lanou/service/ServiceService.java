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

    // 分页
    PageInfo<Service> getPageinfo(Integer pageNo, Integer pageSize);
}