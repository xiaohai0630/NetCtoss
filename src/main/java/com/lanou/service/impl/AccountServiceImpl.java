package com.lanou.service.impl;

import com.lanou.bean.Account;
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

}
