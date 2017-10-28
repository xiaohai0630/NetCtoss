package com.lanou.service.impl;

import com.lanou.bean.ModuleInfo;
import com.lanou.mapper.ModuleInfoMapper;
import com.lanou.service.ModuleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {

    @Resource
    private ModuleInfoMapper moduleInfoMapper;


    // 查询全部
    @Override
    public List<ModuleInfo> findAllModuleInfo(ModuleInfo moduleInfo) {
        return moduleInfoMapper.findModuleInfoByName(moduleInfo);
    }

}
