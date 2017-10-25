package com.lanou.mapper;

import com.lanou.bean.Account;

import java.util.List;

public interface AccountMapper {
    // 删除
    int deleteByPrimaryKey(Integer accountId);

    // 添加
    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    // 修改
    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    // 查询全部
    List<Account> findAllAccount(Account account);

    // 按条件查询
    List<Account> findSomeAccount(Account account);
}