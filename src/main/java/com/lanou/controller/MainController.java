package com.lanou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/10/24.
 */
@Controller
public class MainController {

    // 启动直接到index页面
    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    // 跳转主页
    @RequestMapping(value = "/fee_index")
    public String index() {
        return "index";
    }

    // 资费表相关跳转：
    // 跳转资费列表页面
    @RequestMapping(value = "/fee_list")
    public String costShow() {
        return "fee/fee_list";
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

    // 账务账号相关跳转：
    @RequestMapping(value = "/account_list")
    public String accountShow(){
        return "account/account_list";
    }


}
