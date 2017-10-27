package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.RoleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleInfoController {

    @Resource
    private RoleInfoService roleInfoService;


    // 显示全部信息
    @ResponseBody
    @RequestMapping(value = "/showAllRole")
    public PageInfo<RoleInfo> showAllRole(@RequestParam("no") Integer pageNo,
                                          @RequestParam("size") Integer pageSize) {

        // 加载页面的时候直接显示第一页
        return roleInfoService.getPageinfo(pageNo, pageSize);
    }

}
