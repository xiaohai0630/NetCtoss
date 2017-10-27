package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.mapper.RoleInfoMapper;
import com.lanou.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper roleInfoMapper;

    // 查询全部
    @Override
    public List<RoleInfo> findAllRoleInfo(RoleInfo roleInfo) {
        return roleInfoMapper.findAllRoleInfo(roleInfo);
    }

    // 分页
    @Override
    public PageInfo<RoleInfo> getPageinfo(Integer pageNo, Integer pageSize) {

        return queryCostByPage(pageNo, pageSize);
    }

    // 参数是要查询的表所对应的实体类
    public PageInfo<RoleInfo> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数合法性：默认页数1 默认每页大小6
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 6 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的资费信息，此时cost全为null
        RoleInfo roleInfo = new RoleInfo();
        List<RoleInfo> roleInfoList = roleInfoMapper.findAllRoleInfo(roleInfo);

        // 使用PageInfo对结果进行包装
        // 参数：全部信息的集合
        PageInfo<RoleInfo> pageInfo = new PageInfo<RoleInfo>(roleInfoList);

        return pageInfo;
    }
}
