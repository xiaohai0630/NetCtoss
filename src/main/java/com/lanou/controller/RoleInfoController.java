package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.ModuleInfoService;
import com.lanou.service.RoleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleInfoController {

    @Resource
    private RoleInfoService roleInfoService;

    @Resource
    private ModuleInfoService moduleInfoService;

    // 显示全部信息
    @ResponseBody
    @RequestMapping(value = "/showAllRole")
    public PageInfo<RoleInfo> showAllRole(@RequestParam("no") Integer pageNo,
                                          @RequestParam("size") Integer pageSize) {

        // 加载页面的时候直接显示第一页
        return roleInfoService.getPageinfo(pageNo, pageSize);
    }

    // 添加新的角色
    @ResponseBody
    @RequestMapping(value = "/addNewRole", method = RequestMethod.POST)
    public void addNewRole(RoleInfo roleInfo,
                           @RequestParam("roles") String moduleName,
                           HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        // 编码
        request.setCharacterEncoding("utf-8");

        // 查询到的权限id
        List<ModuleInfo> moduleInfoList = new ArrayList<>();

        // 查询每个权利名称对应的权利id
//        for (int i = 0; i < moduleName.size(); i++) {
//
//        }


        System.out.println("roleInfo：---" + roleInfo);
        System.out.println("moduleName：---" + moduleName);
    }

}
