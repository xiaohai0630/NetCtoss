package com.lanou.service.impl;

import com.lanou.bean.RoleInfo;
import com.lanou.bean.RoleModule;
import com.lanou.mapper.RoleModuleMapper;
import com.lanou.service.RoleModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/28.
 */
@Service
public class RoleModuleServiceImpl implements RoleModuleService {

    @Resource
    private RoleModuleMapper roleModuleMapper;


    // 存储角色和权限的关系
    @Override
    public Integer saveRoleModule(RoleModule roleModule) {
        return roleModuleMapper.insert(roleModule);
    }

    // 根据角色表删除中间表的内容
    @Override
    public Integer delRoleInMiddleTable(RoleInfo roleInfo) {
        return roleModuleMapper.delRoleInMiddleTable(roleInfo);
    }

}
