package com.lanou.service;

import com.lanou.bean.ModuleInfo;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public interface ModuleInfoService {

    // 查询全部
    List<ModuleInfo> findAllModuleInfo(ModuleInfo moduleInfo);
}
