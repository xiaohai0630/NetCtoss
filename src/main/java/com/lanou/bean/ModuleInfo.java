package com.lanou.bean;

import java.util.List;

public class ModuleInfo {

    // 权利id
    private Integer moduleId;
    // 权利名称
    private String name;

    // 和角色表是多对多的关系
    private List<RoleInfo> roleInfoList;

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "ModuleInfo{" +
                "moduleId=" + moduleId +
                ", name='" + name + '\'' +
                ", roleInfoList=" + roleInfoList +
                '}';
    }

}
