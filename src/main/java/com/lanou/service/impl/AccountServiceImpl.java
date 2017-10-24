package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;


    // 查询全部
    public List<Account> findAllAccount(Account account) {

        List<Account> accountList = accountMapper.findAllAccount(account);

        return accountList;
    }

    // 删除
    public Integer delAccount(Integer id) {

        Integer del = accountMapper.deleteByPrimaryKey(id);

        return del;
    }


    // 分页
    // 目标：PageInfo对象
    public List<Account> findWithPageInfo(Integer pageNo, Integer pageSize) {

        PageInfo<Account> pageInfo = queryCostByPage(pageNo, pageSize);
        // 要查询的页面和这个页面的信息
        return pageInfo.getList();
    }

    // 获取分页信息－－为了做显示页面的部分
    public PageInfo<Account> getPageinfo(Integer pageSize) {

        return queryCostByPage(null, pageSize);
    }

    // 参数是要查询的表所对应的实体类
    public PageInfo<Account> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数合法性：默认页数1 默认每页大小3
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 3 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的资费信息，此时cost全为null
        Account account = new Account();
        List<Account> accountList = accountMapper.findAllAccount(account);

        // 使用PageInfo对结果进行包装
        // 参数：全部信息的集合
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);

        return pageInfo;
    }

}
