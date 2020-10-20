package com.crud.controller;

import com.crud.bean.Department;
import com.crud.bean.Msg;
import com.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理部门信息的控制器
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
       List<Department> list = departmentService.getDepts();
       return Msg.success().add("depts",list);
    }
}
