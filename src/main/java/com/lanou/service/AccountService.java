package com.lanou.service;

import com.lanou.bean.Account;

import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
public interface AccountService {

    // 查询全部
    List<Account> findAllAccount(Account account);

    // 删除

}
