package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;

import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
public interface AccountService {

    // 查询全部
    List<Account> findAllAccount(Account account);

    // 查询部分
    List<Account> findSomeAccount(Account account);

    // 删除
    Integer delAccount(Integer id);

    // 添加
    Integer addAccount(Account account);

    // 修改
    Integer changeAccount(Account account);

    // 分页
    PageInfo<Account> getPageinfo(Integer pageNo, Integer pageSize);
}
