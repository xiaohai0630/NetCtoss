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

    // 跳转详细信息
    @RequestMapping(value = "/fee_detail")
    public String detail(){
        return "fee/fee_detail";
    }

    // 显示全部的租赁信息－－分页之后的第一页
    @ResponseBody
    @RequestMapping(value = "/showAllCost")
    public List<Cost> showAllCost(Cost cost) {

        // 显示全部
        List<Cost> costList = costService.findAllCost(cost);

        return costList;

//        return costService.getPageinfo(pageSize);

//        return costService.findWithPageInfo(pageNo,pageSize);
    }

    // 分页
    @ResponseBody
    @RequestMapping(value = "/pageinfo")
    public PageInfo<Cost> pageInfo(@RequestParam("pages") Integer pageSize){
        return costService.getPageinfo(pageSize);
    }

    // 调用显示当前cost
    @ResponseBody
    @RequestMapping(value = "/showThisCost")
    public String showThisCost(Cost cost,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        // 获取session
        HttpSession session = request.getSession();
        // 获取页面传过来的id
        Integer id = cost.getCostId();
        // 把cost的id存在session中
        session.setAttribute("thisCost",id);

        return "redirect:fee/fee_detail";
    }

    // 显示当前cost
    @ResponseBody
    @RequestMapping(value = "/showThisCostList")
    public Cost showThisCostList(HttpServletRequest request,HttpServletResponse response){
        // 获取session中的id
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("thisCost");

        Cost cost = new Cost();
        cost.setCostId(id);
        List<Cost> costList = costService.findAllCost(cost);

        return costList.get(0);
    }

    // 添加资费页面相关操作
    // 一、添加
    @ResponseBody
    @RequestMapping(value = "/addCost",method = RequestMethod.POST)
    public Integer addCost(Cost cost) throws ServletException, IOException {
        // 改时间的格式？
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        // 默认状态是1；创建时间是系统当前时间
        cost.setStatus("1");
        cost.setCreatime(date);

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

}
