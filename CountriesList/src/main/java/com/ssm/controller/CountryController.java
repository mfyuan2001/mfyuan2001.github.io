package com.ssm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Country;
import com.ssm.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @RequestMapping(value = {"/index","/","/index.html"})
    public String getAllCountry(HttpServletRequest request) throws ServletException, IOException {
        PageHelper.startPage(1,10);
        List<Country> all = countryService.selectByExample(null);
        PageInfo pageInfo = new PageInfo(all,5);
        request.setAttribute("states",pageInfo.getList());
        request.setAttribute("pageInfo",pageInfo);
        return "list";
    }

    @RequestMapping("/index/{orderBy}")
    public String getAllCountryOrderBy(@PathVariable("orderBy") String orderBy,Map<String,Object> map){
        PageHelper.startPage(1,10);
        Example example = new Example(Country.class);
        example.orderBy(orderBy).asc();
        List<Country> allSort = countryService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(allSort,5);
        map.put("states", allSort);
        map.put("pageInfo",pageInfo);
        return "list";
    }

    @RequestMapping("/getConditions")
    public String getLikeConditions(Map<String,Object> map, Country country,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        //每页的数量pageSize   pageNum在那一页
        PageHelper.startPage(pageNum,pageSize);
        List<Country> conditions = null;
        if(StringUtil.isNotEmpty(country.getCountryname())&&StringUtil.isNotEmpty(country.getCountrycode())){
           conditions = countryService.getConditions(country);
            System.out.println(conditions);
        }else{
           conditions = countryService.selectByExample(null);
        }
        //默认的分页导航有8页
        PageInfo pageInfo = new PageInfo(conditions,5);
        map.put("states", pageInfo.getList());
        map.put("country", country);
        map.put("pageInfo",pageInfo);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return "list";
    }
}
