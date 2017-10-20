package com.lanou.controller;

import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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


    // 显示全部的租赁信息
    @ResponseBody
    @RequestMapping(value = "/showAllCost")
    public List<Cost> showAllCost(){

        List<Cost> costList = costService.findAllCost();


        return costList;
    }

}
