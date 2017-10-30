package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.service.AdminInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/29.
 */
@Controller
public class AdminInfoController {

    @Resource
    private AdminInfoService adminInfoService;

    // 分页显示
    // 通过管理员查询管理员所对应的登录名
    @ResponseBody
    @RequestMapping(value = "/pageinfoAdmin")
    public PageInfo<AdminInfo> pageInfo(@RequestParam("no") Integer pageNo,
                                        @RequestParam("size") Integer pageSize) {
        return adminInfoService.getPageinfo(pageNo, pageSize);
    }


    // 保存新的管理员
    @ResponseBody
    @RequestMapping(value = "/saveNewAdmin")
    public Integer saveNewAdmin(AdminInfo adminInfo,@RequestParam("modules") String modules){

        System.out.println(adminInfo);
        System.out.println(modules);

        return 0;
    }

}
