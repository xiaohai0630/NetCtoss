package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;

/**
 * Created by dllo on 17/10/29.
 */
public interface AdminInfoService {

    // 分页
    PageInfo<AdminInfo> getPageinfo(Integer pageNo, Integer pageSize);

}
