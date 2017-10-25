package com.lanou.mapper;

import com.lanou.bean.Account;

import java.util.List;

public interface AccountMapper {
    // 删除
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    // 查询全部
    List<Account> findAllAccount(Account account);
}