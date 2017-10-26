package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import com.lanou.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;


    // 分页显示
    @ResponseBody
    @RequestMapping(value = "/pageinfoService")
    public PageInfo<Service> pageInfo(@RequestParam("no") Integer pageNo,
                                      @RequestParam("size") Integer pageSize){
        return serviceService.getPageinfo(pageNo,pageSize);
    }

    // 显示级联查询到的内容

}
