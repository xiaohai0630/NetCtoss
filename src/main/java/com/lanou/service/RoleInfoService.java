package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public interface RoleInfoService {

    // 显示全部
    List<RoleInfo> findAllRoleInfo(RoleInfo roleInfo);

    // 分页
    PageInfo<RoleInfo> getPageinfo(Integer pageNo, Integer pageSize);
}
