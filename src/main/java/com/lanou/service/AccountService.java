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

    // 删除
    Integer delAccount(Integer id);

    // 分页
    PageInfo<Account> getPageinfo(Integer pageNo, Integer pageSize);
}
