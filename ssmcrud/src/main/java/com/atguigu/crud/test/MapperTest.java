package com.atguigu.crud.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * 使用spring4的单元测试
 * @author zgl
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Test
	public void testinsert(){
		//departmentMapper.
		int i = departmentMapper.insert(new Department(null, "催收部"));
		System.out.println("MapperTest.testDepartment()"+i);
	}
	@Test
	public void testupdate(){
		//departmentMapper.
		departmentMapper.updateByPrimaryKey(new Department(3, "销售部"));
		//System.out.println("MapperTest.testDepartment()"+i);
	}
	@Test
	public void testdelete(){
		//departmentMapper.
		departmentMapper.deleteByPrimaryKey(1);
		//System.out.println("MapperTest.testDepartment()"+i);
	}
	
	@Test
	public void testselect(){
		Department department = departmentMapper.selectByPrimaryKey(6);
		System.out.println("MapperTest.testselect()"+department);
	}
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Test
	public void testEminsert(){
		employeeMapper.insertSelective(new Employee(null, "curry", "1", "cy@qq.com", 6));
	}
	
	@Test
	public void testEmselect(){
		Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
		System.out.println("MapperTest.testEmselect()"+employee);
	}
	@Test
	public void testEmselectall(){
		List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
		for (Employee employee : employees) {
			System.out.println("MapperTest.testEmselectall()"+employee);
		}
		//System.out.println("MapperTest.testEmselect()"+employee);
	}
	
	//<!--批量执行的sqlsession-->
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testSqlsession(){
	  EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
	  for(int i=0;i<1000;i++){
		  String uid = UUID.randomUUID().toString().substring(0, 5);
		  mapper.insertSelective(new Employee(null, uid, "0",uid+"@qq.com", 6));
	  }
	}
	
}
