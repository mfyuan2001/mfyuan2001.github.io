package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Country;
import com.ssm.dao.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

import java.time.Period;
import java.util.List;

@Service
public class CountryService extends BaseService<Country> {
    private static String PERCENT = "%";

    public List<Country> getConditions(Country country){
        Example example = new Example(Country.class);
        Criteria criteria = example.createCriteria();
        String countryname = country.getCountryname();
        String countrycode = country.getCountrycode();
        if (!countryname.equals("") ||countryname !=null){
            criteria.andLike("countryname",PERCENT+countryname+PERCENT );
        }
        if (!countrycode.equals("") ||countrycode !=null){
            criteria.andLike("countrycode", PERCENT+countrycode+PERCENT);
        }
        List<Country> countries = selectByExample(example);
        return countries;
    }
}
