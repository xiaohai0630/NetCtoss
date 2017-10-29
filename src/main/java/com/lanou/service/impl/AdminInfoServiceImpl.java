package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.mapper.AdminInfoMapper;
import com.lanou.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/29.
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Resource
    private AdminInfoMapper adminInfoMapper;


    // 获取分页信息－－为了做显示页面的部分
    @Override
    public PageInfo<AdminInfo> getPageinfo(Integer pageNo, Integer pageSize) {

        return queryCostByPage(pageNo, pageSize);
    }

    // 参数是要查询的表所对应的实体类
    public PageInfo<AdminInfo> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数合法性：默认页数1 默认每页大小5
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的管理员信息
        AdminInfo adminInfo = new AdminInfo();
        List<AdminInfo> adminInfoList = adminInfoMapper.findAllAdminInfo(adminInfo);

        // 使用PageInfo对结果进行包装
        // 参数：全部信息的集合
        PageInfo<AdminInfo> pageInfo = new PageInfo<AdminInfo>(adminInfoList);

        return pageInfo;
    }


}
