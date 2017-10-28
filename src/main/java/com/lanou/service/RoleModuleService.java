package com.lanou.service;

import com.lanou.bean.RoleModule;

/**
 * Created by dllo on 17/10/28.
 */
public interface RoleModuleService {


    // 存储角色和权限的对应关系
    Integer saveRoleModule(RoleModule roleModule);

}
