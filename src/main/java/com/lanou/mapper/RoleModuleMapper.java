package com.lanou.mapper;

import com.lanou.bean.RoleInfo;
import com.lanou.bean.RoleModule;

public interface RoleModuleMapper {

    // 存储
    int insert(RoleModule record);

    int insertSelective(RoleModule record);

    // 根据角色删除中间表的内容
    Integer delRoleInMiddleTable(RoleInfo roleInfo);

}