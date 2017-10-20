package com.lanou.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface PageInfoService<T> {

    List<T> findWithPageInfo(Integer pageNo,Integer pageSize);

    PageInfo<T> getPageinfo(Integer pageSize);

}
