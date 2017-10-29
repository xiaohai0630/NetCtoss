package com.lanou.controller;

import com.lanou.bean.ModuleInfo;
import com.lanou.service.ModuleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class ModuleInfoController {

    @Resource
    private ModuleInfoService moduleInfoService;


    // 查询全部的权限的名称
    @ResponseBody
    @RequestMapping(value = "/findAllModuleName")
    public List<ModuleInfo> findAllModuleName(ModuleInfo moduleInfo){

        return moduleInfoService.findAllModuleInfo(moduleInfo);
    }

}
