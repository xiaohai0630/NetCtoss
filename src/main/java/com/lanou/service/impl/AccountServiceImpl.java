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


    // 查询全部、按id查询
    public List<Account> findAllAccount(Account account) {

        return accountMapper.findAllAccount(account);
    }

    // 根据条件查询部分内容
    public List<Account> findSomeAccount(Account account) {

        return accountMapper.findSomeAccount(account);
    }

    // 删除
    public Integer delAccount(Integer id) {

        return accountMapper.deleteByPrimaryKey(id);
    }

    // 添加
    public Integer addAccount(Account account) {

        return accountMapper.insert(account);
    }

    // 修改
    public Integer changeAccount(Account account) {

        return accountMapper.updateByPrimaryKeySelective(account);
    }

    // 获取分页信息－－为了做显示页面的部分
    public PageInfo<Account> getPageinfo(Integer pageNo, Integer pageSize) {

        return queryCostByPage(pageNo, pageSize);
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
