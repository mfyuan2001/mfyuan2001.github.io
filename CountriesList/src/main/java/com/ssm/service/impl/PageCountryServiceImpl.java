package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.bean.Country;
import com.ssm.service.PageCountryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service("pageService")
public class PageCountryServiceImpl extends BaseService<Country> implements PageCountryService {

    private static String PERCENT = "%";

    @Override
    public List<Country> selectByCountry(Country country, int pageNum, int pageSize) {
        Example example = getExample(country);
        PageHelper.startPage(pageNum,pageSize);
        return selectByExample(example);
    }

    private Example getExample(Country country) {
        Example example = new Example(Country.class);
        Criteria criteria = example.createCriteria();
        String countryname = country.getCountryname();
        String countrycode = country.getCountrycode();
        if (StringUtil.isNotEmpty(countryname)) {
            criteria.andLike("countryname", PERCENT + countryname + PERCENT);
        }
        if (StringUtil.isNotEmpty(countrycode)) {
            criteria.andLike("countrycode", PERCENT + countrycode + PERCENT);
        }
        return example;
    }

    @Override
    public List<Country> selectBySort(Country country,String orderBy,String sort, Integer pageNum, Integer pageSize) {
        Example example = getExample(country);
        if(sort.equalsIgnoreCase("asc")){
            example.orderBy(orderBy).asc();
        }else{
            example.orderBy(orderBy).desc();
        }
        PageHelper.startPage(pageNum,pageSize);
        return selectByExample(example);
    }


}
