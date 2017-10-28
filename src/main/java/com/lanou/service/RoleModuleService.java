package com.lanou.service;

import com.lanou.bean.RoleInfo;
import com.lanou.bean.RoleModule;

/**
 * Created by dllo on 17/10/28.
 */
public interface RoleModuleService {

    // 存储角色和权限的对应关系
    Integer saveRoleModule(RoleModule roleModule);

    // 按照角色删除中间表的内容
    Integer delRoleInMiddleTable(RoleInfo roleInfo);

}
