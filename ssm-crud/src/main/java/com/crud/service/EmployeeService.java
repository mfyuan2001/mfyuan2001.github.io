package com.crud.service;

import com.crud.bean.Employee;
import com.crud.bean.EmployeeExample;
import com.crud.bean.EmployeeExample.Criteria;
import com.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll() {
        //根据该列排序
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.setOrderByClause("emp_id");
        return employeeMapper.selectByExampleWithDept(employeeExample);
    }

    /**
     * 添加员工
     * 员工id自增
     * @param employee
     */
    public void addEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    public Employee getEmpByID(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    public int updateEmp(Employee employee) {
        return  employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public int deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 检验用户名是否可用
     * @param empName
     * @return
     */
    public boolean checkEmpName(String empName){
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long l = employeeMapper.countByExample(example);
        return l == 0;
    }

    /**
     * 批量删除
     * @param ids
     */
    public int deleteBacth(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        return employeeMapper.deleteByExample(example);
    }
}
