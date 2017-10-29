package com.lanou.bean;

import java.util.List;

public class RoleInfo {

    // 角色id
    private Integer roleId;
    // 角色名称
    private String name;

    // 和权利ModuleInfo表多对多的关系
    private List<ModuleInfo> moduleInfoList;

    // 和管理员表是多对多的关系
    private List<AdminInfo> adminInfoList;

    public List<AdminInfo> getAdminInfoList() {
        return adminInfoList;
    }

    public void setAdminInfoList(List<AdminInfo> adminInfoList) {
        this.adminInfoList = adminInfoList;
    }

    public List<ModuleInfo> getModuleInfoList() {
        return moduleInfoList;
    }

    public void setModuleInfoList(List<ModuleInfo> moduleInfoList) {
        this.moduleInfoList = moduleInfoList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", moduleInfoList=" + moduleInfoList +
                ", adminInfoList=" + adminInfoList +
                '}';
    }

}
