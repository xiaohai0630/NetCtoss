package com.lanou.mapper;

import com.lanou.bean.RoleInfo;

import java.util.List;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    // 存储
    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    // 查询所有
    List<RoleInfo> findAllRoleInfo(RoleInfo roleInfo);
}