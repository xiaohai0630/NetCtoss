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

    // 显示全部的租赁信息－－分页之后的第一页
    @ResponseBody
    @RequestMapping(value = "/showAllCost")
    public List<Cost> showAllCost(@RequestParam("no") Integer pageNo,
                                  @RequestParam("size") Integer pageSize) {

        // 加载页面的时候直接显示第一页
        return costService.findWithPageInfo(pageNo,pageSize);
    }

    // 分页
    @ResponseBody
    @RequestMapping(value = "/pageinfo")
    public PageInfo<Cost> pageInfo(@RequestParam("pagesize") Integer pageSize){
        return costService.getPageinfo(pageSize);
    }

    // 显示详细信息1－－把id存在session中
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

    // 显示详细信息2－－跳转页面之后调用
    @ResponseBody
    @RequestMapping(value = "/showThisCostList")
    public Cost showThisCostList(HttpServletRequest request,HttpServletResponse response){
        // 获取session中的id
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("thisCost");

        // 查询这个id对应的cost
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

}
