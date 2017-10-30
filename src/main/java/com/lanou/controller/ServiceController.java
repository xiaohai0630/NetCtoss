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
import javax.servlet.http.HttpSession;
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
            if (!service.getLoginPasswd().equals(repwd)) {
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
                service.setStatus("0");

                return serviceService.saveService(service);

            } else {
                // 有空的值，直接返回0
                return 0;
            }

        } catch (Exception e) {
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

    // 修改状态
    @ResponseBody
    @RequestMapping(value = "/changeService")
    public Integer changeService(Service service) {

        try {
            // 开通0；暂停1－－两种状态之间切换
            if (service.getStatus().equals("0")) {
                // 现在是开通状态，需要改为暂停状态
                service.setPauseDate(new Date());
                service.setStatus("1");

                // 返回1
                return serviceService.changeService(service);
            } else if (service.getStatus().equals("1")) {
                // 开通状态需要暂停
                service.setStatus("0");

                // 返回1
                return serviceService.changeService(service);
            }
        } catch (Exception e) {
            //
            return 0;
        }
        return 0;
    }

    // 删除－－改状态，不是真的删除
    @ResponseBody
    @RequestMapping(value = "/delService")
    public Integer delService(Service service) {

        // 修改状态码，并且添加删除时间
        service.setCloseDate(new Date());
        service.setStatus("2");

        return serviceService.changeService(service);
    }

    // 查询符合条件的内容
    @ResponseBody
    @RequestMapping(value = "/findServiceCondition", method = RequestMethod.POST)
    public List<Service> findServiceCondition(Service service,
                                              @RequestParam("idcard") String idCard,
                                              @RequestParam("statusService") String sta,
                                              HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        // 编码
        request.setCharacterEncoding("utf-8");

        // 查询身份证号
        if (idCard != null) {
            // 根据身份证号查询账号id－－转换为账务账号去查询
            Account account = new Account();
            account.setIdcardNo(idCard);
            List<Account> accountList = accountService.findAllAccount(account);

            if (accountList.size() > 0) {
                // 能查询到，有这个身份证对应的账号id
                service.setAccountId(accountList.get(0).getAccountId());
            } else {
                // 不能查询到，没有这个身份证对应的账号id
                service.setAccountId(0);
            }

        }

        // 查询状态
        if (sta != null) {

            switch (sta) {
                case "暂停":
                    service.setStatus("1");
                    break;
                case "开通":
                    service.setStatus("0");
                    break;
                case "删除":
                    service.setStatus("2");
                    break;
                default:
                    service.setStatus(null);
            }

        }

        // 返回一个集合
        return serviceService.findSomeService(service);
    }

    // 显示详细内容－－1、存session
    @ResponseBody
    @RequestMapping(value = "/showServiceSession")
    public Integer showService(Service service, HttpServletRequest request, HttpServletResponse response) {

        // 存的service包含serviceId、accountId、costId
        HttpSession session = request.getSession();
        session.setAttribute("thisService", service);

        return 0;
    }

    // 显示详细内容－－2、取session中的内容
    @ResponseBody
    @RequestMapping(value = "/showServiceDetail")
    public Service showServiceDetail(HttpServletRequest request, HttpServletResponse response) {

        // session中的service为了获取serviceId
        HttpSession session = request.getSession();
        Service service = (Service) session.getAttribute("thisService");

        List<Service> serviceList = serviceService.findAllService(service);

        // 查询账号信息
        Account account = new Account();
        account.setAccountId(service.getAccountId());
        List<Account> accountList = accountService.findAllAccount(account);

        // 查询资费信息
        Cost cost = new Cost();
        cost.setCostId(service.getCostId());
        List<Cost> costList = costService.findAllCost(cost);

        // 把account和cost存在返回值中
        Service serviceShow = serviceList.get(0);
        serviceShow.setAccount(accountList.get(0));
        serviceShow.setCost(costList.get(0));

        return serviceShow;
    }

}
