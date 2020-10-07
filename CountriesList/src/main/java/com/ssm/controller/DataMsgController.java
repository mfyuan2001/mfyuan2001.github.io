package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Country;
import com.ssm.bean.Msg;
import com.ssm.service.PageCountryService;
import com.ssm.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.OrderBy;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class DataMsgController {
    private String success = "success";


    @Autowired
    @Qualifier("pageService")
    PageCountryService pageCountryService;

    @RequestMapping("/hello")
    public String hello() {
        return "index";
    }

    @RequestMapping("/init")
    @ResponseBody
    public Msg getAll(Country country, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<Country> countries = pageCountryService.selectByCountry(country, pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(countries,5);
        return Msg.fail().add("pageInfo", pageInfo);
    }


    @RequestMapping("/sort/{orderBy}/{sort}")
    @ResponseBody
    public Msg sortData(Country country,@PathVariable("orderBy") String orderBy,@PathVariable("sort") String sort, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<Country> countries = pageCountryService.selectBySort(country,orderBy,sort, pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(countries,5);
        return Msg.fail().add("pageInfo", pageInfo);
    }
    @RequestMapping("/toSave")
    public String toSave(){
        return success;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Country country){
        if("".equals(country.getCountryname())||"".equals(country.getCountrycode())){
            return "error";
        }
        List<Country> select = pageCountryService.select(country);
        boolean empty = select.isEmpty();
        if(empty){
            pageCountryService.save(country);
            return "index";
        }else{
            return "error";
        }
    }

    @RequestMapping(value = "/toUpdate/{id}",method = RequestMethod.GET)
    public String toUpdate(@PathVariable Integer id, Map<String,Object> map){
        Country country = pageCountryService.selectByKey(id);
        map.put("country", country);
        return success;
    }


    //需要把回来的页面设置为，isErrorPage=true
    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    public String update(Country country){
        pageCountryService.update(country);
        return "index";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id){
        pageCountryService.delete(id);
        return "index";
    }

}
