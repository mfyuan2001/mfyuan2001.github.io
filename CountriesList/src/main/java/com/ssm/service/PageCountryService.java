package com.ssm.service;

import com.ssm.bean.Country;

import java.util.List;

public interface PageCountryService  extends IService<Country>{
    /**
     * 根据条件分页查询
     *
     * @param country
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Country> selectByCountry(Country country, int pageNum, int pageSize);


    /**
     * 对字段进行排序
     * @param orderBy
     * @param sort
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Country> selectBySort(Country country,String orderBy,String sort,Integer pageNum,Integer pageSize);

}
