package com.lanou.controller;

import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    /**
     *
     */

    @Resource
    private CostService costService;

    // 登录直接到index页面
    @RequestMapping(value = "/")
    public String home(){

        return "index";
    }

    // 主页面跳转cost展示页面
    @RequestMapping(value = "/fee_list")
    public String costShow(){
        return "fee/fee_list";
    }

    // cost展示页面跳转主页
    @RequestMapping(value = "/fee_index")
    public String index(){
        return "index";
    }


    // 显示全部的租赁信息－－按照分页的形式写
    @ResponseBody
    @RequestMapping(value = "/showAllCost")
    public List<Cost> showAllCost(){

        List<Cost> costList = costService.findAllCost();

        System.out.println(costList);

        // 每一页的大小是2
//        Integer pageSize = 2;
//        return costService.getPageinfo(pageSize);

        return costList;
    }

}
