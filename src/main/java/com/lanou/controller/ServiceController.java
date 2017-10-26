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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @RequestMapping(value = "/addService")
    public Integer saveService(Service service){

        return serviceService.saveService(service);
    }

    // 添加2－－查询账务账号
    @ResponseBody
    @RequestMapping(value = "/findAccountInService")
    public Account findAccountInService(Account account){
        
        // 通过身份证查询账务账号
        List<Account> accountList = accountService.findAllAccount(account);
        return accountList.get(0);
    }

    // 添加3－－查询资费类型
    @ResponseBody
    @RequestMapping(value = "/findCostNameInService")
    public List<Cost> findCostNameInService(Cost cost){
        return costService.findAllCost(cost);
    }

}
