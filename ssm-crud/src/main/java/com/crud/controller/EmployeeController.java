package com.crud.controller;

import com.crud.bean.Employee;
import com.crud.bean.Msg;
import com.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * 处理员工CRUD请求
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    /**
     * 查询员工数据(分页查询)
     * @return
     */
    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpServletRequest request){
        //引入pageHelper插件
        //在查询之前只需要调用  传入页码，以及每页的大小
        //所有数据每页分5个跳转到pn页
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结构，只需要将pageInfo交给页面就行了
        //封装了详细分页信息，包括有我们查询出来的数据,传入连续显示的页数
        //这里默认的显示记录数是8  这里可以显示的页面是5
        PageInfo pageInfo = new PageInfo(emps,5);
        request.setAttribute("pageInfo",pageInfo);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emps",method = RequestMethod.POST)
    @ResponseBody
    public Msg addEmp(@Valid Employee employee, BindingResult bindingResult) {
        Map<String, Object> error = new HashMap<String, Object>();
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                error.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        ArrayList arrayList = new ArrayList(error.keySet());
        //既没有格式错误也没有相同名称的错误就直接发送错误信息页面
        if(employeeService.checkEmpName(employee.getEmpName())){
            if(arrayList.size() == 0){
                employeeService.addEmp(employee);
                return Msg.success();
            }else{
                return Msg.fail().add("errors", error);
            }
        }else{
            error.put("empName1","用户名已存在");
            return Msg.fail().add("errors",error);
        }
    }
    /**
     * 修改员工
     * @param employee
     * @return
     */
   @RequestMapping(value = "/emps",method = RequestMethod.PUT)
   @ResponseBody
    public Msg updateEmp(@Valid Employee employee,BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           List<FieldError> fieldErrors = bindingResult.getFieldErrors();
           Map<String, Object> error = new HashMap<String, Object>();
           for (FieldError fieldError : fieldErrors) {
               error.put(fieldError.getField(), fieldError.getDefaultMessage());
           }
           return Msg.fail().add("errors", error);
       }else{
           employeeService.updateEmp(employee);
           return Msg.success();
       }
    }

    /**
     * 根据id拿到员工信息
     * @param id
     * @return
     */
    @RequestMapping("/empById/{id}")
    @ResponseBody
    public Msg getEmpById(@PathVariable Integer id){
        Employee employee =employeeService.getEmpByID(id);
        return Msg.success().add("emp",employee);
    }

    /**
     * 根据id删除员工
     * 批量删除 1-2-3
     * 单个删除 1
     *
     * @param ids
     * @return
     */
    @RequestMapping("/emps/{ids}")
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            ArrayList<Integer> list = new ArrayList<Integer>();
            String[] split = ids.split("-");
            for (String s : split) {
                list.add(Integer.parseInt(s));
            }
            employeeService.deleteBacth(list);
            return Msg.success();
        }else{
            employeeService.deleteEmp(Integer.parseInt(ids));
            return Msg.success();
        }
    }

    /**
     * 根据查询出指定name的count的判断是否可用
     */
    @RequestMapping("/validate")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName") String empName){
        if(employeeService.checkEmpName(empName)){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
    /**
     * 查询员工数据(分页查询)
     * @return
     */
    /*@RequestMapping("/getEmps")
    public  String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpServletRequest request){

        //引入pageHelper插件
        //在查询之前只需要调用  传入页码，以及每页的大小
        //所有数据每页分5个跳转到pn页
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结构，只需要将pageInfo交给页面就行了
        //封装了详细分页信息，包括有我们查询出来的数据,传入连续显示的页数
        //这里默认的显示记录数是8  这里可以显示的页面是5
        PageInfo pageInfo = new PageInfo(emps,5);
        request.setAttribute("pageInfo",pageInfo);
        return "list";
    }*/
}
