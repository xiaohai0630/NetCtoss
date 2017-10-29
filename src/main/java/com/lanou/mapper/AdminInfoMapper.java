package com.lanou.mapper;

import com.lanou.bean.AdminInfo;

import java.util.List;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    // 显示全部
    List<AdminInfo> findAllAdminInfo(AdminInfo adminInfo);

}