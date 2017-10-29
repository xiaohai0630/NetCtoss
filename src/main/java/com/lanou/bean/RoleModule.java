package com.lanou.bean;

public class RoleModule {

    // 角色表和权限表的中间表

    // 角色表id
    private Integer roleId;
    // 权限表id
    private Integer moduleId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return "RoleModule{" +
                "roleId=" + roleId +
                ", moduleId=" + moduleId +
                '}';
    }

}
