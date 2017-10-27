package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.bean.Service;
import com.lanou.service.AccountService;
import com.lanou.service.CostService;
import com.lanou.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @Resource
    private AccountService accountService;

    @Resource
    private CostService costService;

    // 分页显示
    @ResponseBody
    @RequestMapping(value = "/pageinfoService")
    public PageInfo<Service> pageInfo(@RequestParam("no") Integer pageNo,
                                      @RequestParam("size") Integer pageSize) {

        return serviceService.getPageinfo(pageNo, pageSize);
    }

    // 添加1－－保存添加的信息
    @ResponseBody
    @RequestMapping(value = "/addService", method = RequestMethod.POST)
    public Integer saveService(@RequestParam("idcard") String idCard,
                               @RequestParam("costname") String costName,
                               @RequestParam("repwd") String repwd,
                               Service service,
                               HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        System.out.println(repwd);
        // 处理乱码
        request.setCharacterEncoding("utf-8");

        // 每一条都不能为空才可以保存！
        try {
            // 两次密码一致
            if (!service.getLoginPasswd().equals(repwd)){
                return 2;
            }

            if (!idCard.equals(null) && !costName.equals(null) && !service.getUnixHost().equals(null)
                    && !service.getOsUsername().equals(null) && !service.getLoginPasswd().equals(null)
                    && !repwd.equals(null)) {

                // account查询账号id
                Account account = new Account();
                account.setIdcardNo(idCard);
                Integer accountId = accountService.findAllAccount(account).get(0).getAccountId();

                // cost查询资费id，添加进service表
                Cost cost = new Cost();
                cost.setName(costName);
                Integer costId = costService.findAllCost(cost).get(0).getCostId();
                service.setCostId(costId);

                // 添加开通时间、开通状态（0）
                service.setCreateDate(new Date());
                service.setAccountId(accountId);

                return serviceService.saveService(service);

            } else {
                // 有空的值，直接返回0
                return 0;
            }

        }catch (Exception e){
            return 0;
        }

    }

    // 添加2－－查询账务账号
    @ResponseBody
    @RequestMapping(value = "/findAccountInService")
    public Account findAccountInService(Account account) {
        // 通过身份证查询账务账号
        List<Account> accountList = accountService.findAllAccount(account);

        if (accountList.size() > 0) {
            return accountList.get(0);
        }
        return null;
    }

    // 添加3－－查询资费类型
    @ResponseBody
    @RequestMapping(value = "/findCostNameInService")
    public List<Cost> findCostNameInService(Cost cost) {
        return costService.findAllCost(cost);
    }

    // 添加4－－判断密码和重复密码是否一致
    @ResponseBody
    @RequestMapping(value = "/servicePwd")
    public boolean servicePwd(@RequestParam("pwd") String pwd,
                              @RequestParam("repwd") String repwd) {

        if (pwd != null && repwd != null) {
            return pwd.equals(repwd);
        } else {
            return false;
        }

    }

}
