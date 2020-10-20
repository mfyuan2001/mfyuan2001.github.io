package com.crud.test;

import com.crud.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 使用Spring测试模板提供的测试请求功能，测试curd请求的正确性
 * Spring4测试的时候需要servlet3.0以上的支持
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MvcTest {

    //传入Springmvc的IOC
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取到处理结果
    MockMvc mockMvc;

    @Before
    public void initMokcMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getEmps").param("pn", "1")).andReturn();

        //请求成功以后请求域中会有pageInfo，我们可以去除pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前的页面数"+pageInfo.getPageNum());
        System.out.println("总页数"+pageInfo.getPages());
        System.out.println("每一页的记录数"+pageInfo.getPageSize());
        System.out.println("当前页面的记录数"+pageInfo.getSize());
        System.out.println("总记录数"+pageInfo.getTotal());
        System.out.println("当前可以显示数据第一页的页码"+pageInfo.getNavigateFirstPage());
        System.out.println("当前可以显示数据最后一页的页码"+pageInfo.getNavigateLastPage());
        System.out.println("当前可显示的页码数"+pageInfo.getNavigatePages());
        for (int i : pageInfo.getNavigatepageNums()) {
            System.out.println("页码数："+i);
        }
        //当前页的数据
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println("ID:"+employee.getEmpId()+"==>Name:"+employee.getEmpName());
        }
    }
}
