package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    /**
     * 资费信息：
     */

    @Resource
    private CostService costService;

    // 分页显示
    @ResponseBody
    @RequestMapping(value = "/pageinfoCost")
    public PageInfo<Cost> pageInfo(@RequestParam("no") Integer pageNo,
                                   @RequestParam("size") Integer pageSize){
        return costService.getPageinfo(pageNo,pageSize);
    }

    // 显示详细信息1－－把id存在session中
    @ResponseBody
    @RequestMapping(value = "/showThisCost")
    public String showThisCost(Cost cost,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        // 获取session，把cost存在session中
        HttpSession session = request.getSession();
        session.setAttribute("thisCost",cost);

        return "redirect:fee/fee_detail";
    }

    // 显示详细信息2－－跳转页面之后调用
    @ResponseBody
    @RequestMapping(value = "/showThisCostList")
    public Cost showThisCostList(HttpServletRequest request,HttpServletResponse response){
        // 获取session中的cost，查询相应的信息
        HttpSession session = request.getSession();
        Cost cost = (Cost) session.getAttribute("thisCost");
        List<Cost> costList = costService.findAllCost(cost);

        return costList.get(0);
    }

    // 添加资费页面相关操作
    // 一、添加
    @ResponseBody
    @RequestMapping(value = "/addCost",method = RequestMethod.POST)
    public Integer addCost(Cost cost) throws ServletException, IOException {
        // 默认状态是1；创建时间是系统当前时间，展示的时候需要改变格式
        cost.setStatus("1");
        cost.setCreatime(new Date());

        Integer save = costService.saveCost(cost);

        return save;
    }

    // 二、删除
    @ResponseBody
    @RequestMapping(value = "/delCost")
    public Integer delCost(Cost cost){
        // 删除成功之后返回1
        Integer del = costService.delCost(cost.getCostId());
        return del;
    }

    // 三、修改
    // 1、存当前的id
    @ResponseBody
    @RequestMapping(value = "/changeCost")
    public String changeCost(Cost cost,HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("changeCost",cost);

        return "redirect:fee/fee_modi";
    }

    // 2、取当前的cost，显示在页面
    @ResponseBody
    @RequestMapping(value = "/changeCostList")
    public Cost changeCostList(HttpServletRequest request,HttpServletResponse response){
        // 把当前这个cost的详细信息显示在页面上
        HttpSession session = request.getSession();
        Cost cost = (Cost) session.getAttribute("changeCost");
        List<Cost> costList = costService.findAllCost(cost);

        return costList.get(0);
    }

    // 3、将修改后的内容保存



    // 四、启用

}
