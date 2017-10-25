package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
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

    // 显示全部信息
    @ResponseBody
    @RequestMapping(value = "/pageinfoAccount")
    public PageInfo<Account> showAllAccount(@RequestParam("no") Integer pageNo,
                                            @RequestParam("size") Integer pageSize){

        // 加载页面的时候直接显示第一页
        return accountService.getPageinfo(pageNo,pageSize);
    }

    // 查询
    @ResponseBody
    @RequestMapping(value = "/findAccount")
    public List<Account> findSomeAccount(Account account){
        
        return accountService.findSomeAccount(account);
    }


    // 删除－－记载删除时间
    @ResponseBody
    @RequestMapping(value = "/delAccount")
    public Integer delAccount(Account account){

        List<Account> accountList = accountService.findAllAccount(account);
        // 删除时间不为空，说明已经删除了
        if (accountList.get(0).getCloseDate() != null){
            return 0;
        }

        // 添加删除时间、状态改为0
        account.setCloseDate(new Date());
        account.setStatus("0");

        // 删除成功之后返回1－－添加删除时间
        return accountService.changeAccount(account);
    }

    // 显示详细信息1－－把id存在session中
    @ResponseBody
    @RequestMapping(value = "/showThisAccount")
    public String showThisAccount(Account account, HttpServletRequest request, HttpServletResponse response){

        // 将当前的account的id存在session中
        HttpSession session = request.getSession();
        Integer id = account.getAccountId();
        session.setAttribute("thisAccount",id);

        return "redirect:account/account_detail";
    }

    // 显示详细信息2－－跳转页面之后调用
    @ResponseBody
    @RequestMapping(value = "/showThisAccountList")
    public Account showThisAccountList(HttpServletRequest request,HttpServletResponse response){
        // 取出session中的accountId
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("thisAccount");

        // 查询这个account
        Account account = new Account();
        account.setAccountId(id);
        List<Account> accountList = accountService.findAllAccount(account);

        return accountList.get(0);
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/addAccount")
    public Integer addAccount(Account account,
                              @RequestParam("referrerID") String referrerID){

        // 找推荐人id
        Account accountReferrer = new Account();
        accountReferrer.setIdcardNo(referrerID);
        List<Account> accountList = accountService.findAllAccount(accountReferrer);

        // 创建时间、状态1（开通）
        account.setCreateDate(new Date());
        account.setStatus("1");
        // 通过身份证找到推荐人的id
        account.setRecommenderId(accountList.get(0).getAccountId());

        return accountService.addAccount(account);
    }

}
