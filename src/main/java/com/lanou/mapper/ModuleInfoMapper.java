package com.lanou.mapper;

import com.lanou.bean.ModuleInfo;

import java.util.List;

public interface ModuleInfoMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(ModuleInfo record);

    int insertSelective(ModuleInfo record);

    ModuleInfo selectByPrimaryKey(Integer moduleId);

    int updateByPrimaryKeySelective(ModuleInfo record);

    int updateByPrimaryKey(ModuleInfo record);

    // 查询全部－－级联
    List<ModuleInfo> findModuleInfo(ModuleInfo moduleInfo);

    // 查询全部－－不级联，只是根据name查询id
    List<ModuleInfo> findModuleInfoByName(ModuleInfo moduleInfo);
}