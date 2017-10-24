package com.lanou.controller;

import com.lanou.bean.Account;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
@Controller
public class AccountController {

    /**
     * 账号管理：
     */

    @Resource
    private AccountService accountService;

    // 显示全部
    @ResponseBody
    @RequestMapping(value = "/showAllAccount")
    public List<Account> showAllAccount(Account account){

        List<Account> accountList = accountService.findAllAccount(account);

        return accountList;
    }

    // 删除
//    public Integer delAccount(){
//
//    }

}
