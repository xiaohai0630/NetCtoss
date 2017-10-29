package com.lanou.mapper;

import com.lanou.bean.RoleInfo;

import java.util.List;

public interface RoleInfoMapper {
    // 通过roleId删除
    int deleteByPrimaryKey(Integer roleId);

    // 存储
    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKey(RoleInfo record);

    // 查询所有
    List<RoleInfo> findAllRoleInfo(RoleInfo roleInfo);

    // 通过管理员查询角色
    List<RoleInfo> findRoleInfo(RoleInfo roleInfo);
}