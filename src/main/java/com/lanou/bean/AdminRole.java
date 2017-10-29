package com.lanou.bean;

public class AdminRole {

    // 管理员表和角色表的中间表

    // 管理员id
    private Integer adminId;

    // 角色id
    private Integer roleId;


    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "adminId=" + adminId +
                ", roleId=" + roleId +
                '}';
    }

}
