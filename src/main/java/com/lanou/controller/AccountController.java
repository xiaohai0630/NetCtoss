package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.service.AccountService;
import com.lanou.utils.IdCardUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                                            @RequestParam("size") Integer pageSize) {

        // 加载页面的时候直接显示第一页
        return accountService.getPageinfo(pageNo, pageSize);
    }

    // 查询
    @ResponseBody
    @RequestMapping(value = "/findAccount")
    public PageInfo<Account> findSomeAccount(Account account,
                                             @RequestParam("sta") String sta,
                                             @RequestParam("no") Integer pageNo,
                                             @RequestParam("size") Integer pageSize) {

        // 如果不是3，就给account添加对应的状态码，如果是3，状态码就是空
        if (!sta.equals("3")) {
            account.setStatus(sta);
        }
        PageInfo<Account> pageInfo = new PageInfo<Account>();
        pageInfo.setList(accountService.findSomeAccount(account));

        return pageInfo;
    }

    // 删除－－记载删除时间
    @ResponseBody
    @RequestMapping(value = "/delAccount")
    public Integer delAccount(Account account) {
        // 添加删除时间、状态改为2
        account.setCloseDate(new Date());
        account.setStatus("2");

        // 删除成功之后返回1－－添加删除时间
        return accountService.changeAccount(account);
    }

    // 显示详细信息1－－把id存在session中
    @ResponseBody
    @RequestMapping(value = "/showThisAccount")
    public String showThisAccount(Account account, HttpServletRequest request, HttpServletResponse response) {

        // 将当前的account存在session中－－包含的参数：id
        HttpSession session = request.getSession();
        session.setAttribute("thisAccount", account);

        return "redirect:account/account_detail";
    }

    // 显示详细信息2－－跳转页面之后调用
    @ResponseBody
    @RequestMapping(value = "/showThisAccountList")
    public Account showThisAccountList(HttpServletRequest request, HttpServletResponse response) {
        // 取出session中的account
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("thisAccount");

        // 通过id查询这个account的完整的信息
        List<Account> accountList = accountService.findAllAccount(account);

        return accountList.get(0);
    }

    // 显示详细信息3－－查询推荐人身份证
    @ResponseBody
    @RequestMapping(value = "/findRecommenderIdCard")
    public Account findRecommenderIdCard(Account account,HttpServletRequest request,HttpServletResponse response){
        // 只有id
        HttpSession session = request.getSession();
        Account accountSession = (Account) session.getAttribute("thisAccount");

        // 找到这个account的完整信息
        List<Account> accountList = accountService.findAllAccount(accountSession);

        // 把推荐人的id赋给accountID
        Account find = new Account();
        find.setAccountId(accountList.get(0).getRecommenderId());

        // 用新的account查询推荐人
        List<Account> recommender = accountService.findAllAccount(find);

        return recommender.get(0);
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/addAccount")
    public Integer addAccount(Account account, @RequestParam("referrerID") String referrerID) throws ParseException {
        // 职务
        if (account.getOccupation().equals("1")) {
            account.setOccupation("干部");
        } else if (account.getOccupation().equals("2")) {
            account.setOccupation("学生");
        } else if (account.getOccupation().equals("3")) {
            account.setOccupation("技术人员");
        } else {
            account.setOccupation("其他");
        }
        // 性别
        if (account.getGender().equals("1")) {
            account.setGender("女");
        } else if (account.getGender().equals("2")) {
            account.setGender("男");
        } else {
            account.setGender(null);
        }

        // 用推荐人的身份证号查询推荐人的id
        Account accountReferrer = new Account();
        accountReferrer.setIdcardNo(referrerID);
        List<Account> accountList = accountService.findAllAccount(accountReferrer);
        // 如果查询到推荐人，就给新添加的用户添加对应的推荐人id
        if (accountList.size() > 0) {
            // 通过身份证找到推荐人的id
            account.setRecommenderId(accountList.get(0).getAccountId());
        }

        // 创建时间、状态1（开通）
        account.setCreateDate(new Date());
        account.setStatus("1");

        // 先用身份证号计算生日，再将得到的字符串转为date类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = IdCardUtils.getBirthday(account.getIdcardNo());
        Date date = sdf.parse(birthday);

        account.setBirthdate(date);

        return accountService.addAccount(account);
    }

    // 改变状态
    @ResponseBody
    @RequestMapping(value = "/changeStatus")
    public Integer changeStatus(Account account) {
        // status状态码：1:开启，0:暂停，2:删除
        Integer id = account.getAccountId();
        String status = account.getStatus();

        Account newAccount = new Account();
        if (status.equals("1")) {
            // 暂停时间
            newAccount.setPauseDate(new Date());
            newAccount.setStatus("0");
        } else if (status.equals("0")) {
            // 清空暂停时间
//            newAccount.setPauseDate(null);
            newAccount.setStatus("1");
        }
        // 存id
        newAccount.setAccountId(id);

        // 修改并且返回结果
        return accountService.changeAccount(newAccount);
    }

    // 修改详细信息1－－存当前的account的id
    @ResponseBody
    @RequestMapping(value = "/changeThisAccount")
    public String changeThisAccount(Account account, HttpServletRequest request, HttpServletResponse response) {
        // 也存thisAccount？
        HttpSession session = request.getSession();
        session.setAttribute("thisAccount", account);

        return "redirect:account/account_modi";
    }

    // 修改详细信息2－－取出session中的id，找到对应的account内容显示在页面上
    @ResponseBody
    @RequestMapping(value = "/changThisAccountList")
    public Account changThisAccountList(HttpServletRequest request, HttpServletResponse response) {
        // 获取当前这个account
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("thisAccount");

        return accountService.findAllAccount(account).get(0);
    }

    // 修改详细信息3－－保存修改的内容
    @ResponseBody
    @RequestMapping(value = "/changeThisAccountListSave")
    public Integer changeThisAccountListSave(Account account) {

        return accountService.changeAccount(account);
    }

}
