package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.bean.RoleModule;
import com.lanou.service.RoleInfoService;
import com.lanou.service.RoleModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleInfoController {

    @Resource
    private RoleInfoService roleInfoService;

    @Resource
    private RoleModuleService roleModuleService;

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
    public Integer addNewRole(RoleInfo roleInfo,
                              @RequestParam("roles") String moduleName,
                              HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        // 编码
        request.setCharacterEncoding("utf-8");

        // 数组－－获取到的是字符串类型的权限id，用逗号分割么每个id
        String[] str = moduleName.split(",");

        // 判断是否有空的内容
        if (str.length != 0 && roleInfo.getName() != null) {

            // 往中间表中存角色和权限的关系－－都是Integer类型的
            RoleModule roleModule = new RoleModule();

            // 存储当前这个角色－－返回现在存储的这个角色的id
            Integer num = roleInfoService.savaRoleInfo(roleInfo);

            for (int i = 0; i < str.length; i++) {

                roleModule.setRoleId(roleInfo.getRoleId());
                roleModule.setModuleId(Integer.valueOf(str[i]));

                roleModuleService.saveRoleModule(roleModule);
            }

            return num;
        } else {
            return 0;
        }

    }

    // 删除角色，同时删除中间表中的值
    @ResponseBody
    @RequestMapping(value = "/delRoleInTwoTable")
    public Integer delRoleInTwoTable(RoleInfo roleInfo) {

        // 删除中间表中的内容
        roleModuleService.delRoleInMiddleTable(roleInfo);

        // 根据roleId删除
        return roleInfoService.delRole(roleInfo);
    }


    // 修改角色信息－－1、存session
    @ResponseBody
    @RequestMapping(value = "/changeRole")
    public Integer changeRole(RoleInfo roleInfo, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        // 把这个角色的信息存在session中
        List<RoleInfo> roleInfoList = roleInfoService.findAllRoleInfo(roleInfo);
        session.setAttribute("thisRoleInfo", roleInfoList.get(0));

        return 1;
    }

    // 修改角色信息－－2、从session中找到这个角色
    @ResponseBody
    @RequestMapping(value = "/changeRoleFind")
    public RoleInfo changeRoleFind(HttpServletRequest request, HttpServletResponse response) {
        // 将session中的角色取出返回页面
        HttpSession session = request.getSession();
        RoleInfo roleInfo = (RoleInfo) session.getAttribute("thisRoleInfo");

        return roleInfo;
    }

    // 修改角色信息－－3、保存修改信息
}
