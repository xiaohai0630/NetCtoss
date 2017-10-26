package com.lanou.controller;

import com.lanou.utils.IdCardUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

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

    // 计算生日
    @ResponseBody
    @RequestMapping(value = "/birthday")
    public String birthday(@RequestParam("birthday") String referrerId) throws ParseException {

        return IdCardUtils.getBirthday(referrerId);
    }

    // 资费表相关跳转：
    // 跳转资费列表页面
    @RequestMapping(value = "/fee_list")
    public String costShow() {
        return "fee/fee_list";
    }

    // 跳转添加资费页面
    @RequestMapping(value = "/fee_add")
    public String addCost() {
        return "fee/fee_add";
    }

    // 跳转详细信息
    @RequestMapping(value = "/fee_detail")
    public String detailCost(){
        return "fee/fee_detail";
    }

    // 跳转修改cost页面
    @RequestMapping(value = "/fee_modi")
    public String changCost(){
        return "fee/fee_modi";
    }


    // 账务账号相关跳转：
    // 跳转账号列表页面
    @RequestMapping(value = "/account_list")
    public String accountShow(){
        return "account/account_list";
    }

    // 跳转详细信息
    @RequestMapping(value = "/account_detail")
    public String detailAccount(){
        return "account/account_detail";
    }

    // 跳转添加
    @RequestMapping(value = "/account_add")
    public String addAccount(){
        return "account/account_add";
    }

    // 跳转修改页面
    @RequestMapping(value = "/account_modi")
    public String changeAccount(){
        return "account/account_modi";
    }


    // 服务相关跳转
    // 服务列表页面
    @RequestMapping(value = "/service_list")
    public String serviceShow(){
        return "service/service_list";
    }

    // 添加
    @RequestMapping(value = "/service_add")
    public String addService(){
        return "service/service_add";
    }

}
