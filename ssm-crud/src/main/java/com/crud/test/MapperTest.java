package com.crud.test;

import com.crud.bean.Department;
import com.crud.bean.DepartmentExample;
import com.crud.bean.Employee;
import com.crud.dao.DepartmentMapper;
import com.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD(){
        //生成部门信息
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
//        departmentMapper.insertSelective(new Department(null,"运营部"));

        //生成员工信息
//        employeeMapper.insertSelective(new Employee(null,"jim","F","jim@qq.com",1));

//        批量插入多个员工，批量，使用可以执行批量操作的SQLsqlseesion
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Random random = new Random();
        for (int i =0;i<1000;i++){
            String gender = random.nextBoolean() ?"M":"F";
//            System.out.println(gender);
            Integer dId = random.nextInt(3)+1;
//            System.out.println(dId);
            String name = UUID.randomUUID().toString().substring(0, 5)+i;
//            System.out.println(name);
            mapper.insertSelective(new Employee(null,name,gender,name+"@qq.com",dId));
        }
        System.out.println("批量执行完毕");
    }


    @Test
    public void testCRUD2(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectByPrimaryKeyWithDept(666);
        System.out.println(employee);
    }


}
