package com.lanou.controller;

import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    /**
     * 方法：
     * 登录直接跳转index页面：/
     * 跳转cost展示页面：/fee_list
     * 跳转index页面：/fee_index
     * 跳转添加页面：/fee_add
     * 展示全部：/showAllCost
     * 添加cost：/addCost
     */

    @Resource
    private CostService costService;

    // 登录直接到index页面
    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    // 跳转资费展示页面
    @RequestMapping(value = "/fee_list")
    public String costShow() {
        return "fee/fee_list";
    }

    // 跳转主页
    @RequestMapping(value = "/fee_index")
    public String index() {
        return "index";
    }

    // 跳转添加资费页面
    @RequestMapping(value = "/fee_add")
    public String add() {
        return "fee/fee_add";
    }

    // 显示全部的租赁信息
    @ResponseBody
    @RequestMapping(value = "/showAllCost")
    public List<Cost> showAllCost() {

        List<Cost> costList = costService.findAllCost();

        return costList;
    }

    // 添加资费页面相关操作
    // 一、添加
    @RequestMapping(value = "/addCost",method = RequestMethod.POST)
    public Integer addCost(Cost cost) throws ServletException, IOException {

        // 默认状态是1；创建时间是系统当前时间
        cost.setStatus("1");
        cost.setCreatime(new Date());

        System.out.println(cost);

        Integer save = costService.saveCost(cost);

        // 添加成功之后跳转到列表页面
        return save;
    }

    // 二、删除
    @RequestMapping(value = "/delCost")
    public void delCost(Cost cost){

        Integer del = costService.delCost(cost.getCostId());

        // 返回什么？
    }
}
