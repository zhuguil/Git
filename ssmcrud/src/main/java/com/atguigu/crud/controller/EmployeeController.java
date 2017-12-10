package com.atguigu.crud.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 分页查询pagehelper
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(name="pn",defaultValue="1") Integer pn,Model model){
		PageHelper.startPage(pn, 5);
		List<Employee> employees = employeeService.getList();
		PageInfo pageInfo = new PageInfo<>(employees, 5);
		model.addAttribute("pageInfo", pageInfo);
		return "list";
	}
	 
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(name="pn",defaultValue="1") Integer pn){
		PageHelper.startPage(pn, 5);
		List<Employee> employees = employeeService.getList();
		PageInfo pageInfo = new PageInfo<>(employees, 5);
		
		//model.addAttribute("pageInfo", pageInfo);
		return Msg.success().addExtend("pageInfo", pageInfo);
	}
	
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Msg saveEmployees(Employee employee){
		employeeService.saveEmployee(employee);
		return Msg.success();
	}
	
	/**
	 * 查询用户名是否已被注册
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(String empName){
		String rgx = "(^[a-zA-Z0-9_-]{6-16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!empName.matches(rgx)) {
			return Msg.fail().addExtend("va_msg", "java验证错误");
		}
		boolean name = employeeService.checkuse(empName);
		if (name==true) {
			return Msg.success();
		}else {
			return Msg.fail().addExtend("va_msg", "java验证错误");
		}
	}
	
	/**
	 * 根据id查询员工信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public Msg getEmp(@PathVariable("id") Integer id){
		 Employee employee = employeeService.getEmp(id);
		return Msg.success().addExtend("emp", employee);
	}
	
	/**
	 * 更新员工信息
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmp(Employee employee){
		System.out.println(employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 删除单个员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids") String ids){
		if (ids.contains("-")) {
			String[] idArray = ids.split("-");
			List<Integer> listId = new ArrayList<Integer>();
			for (String id : idArray) {
				listId.add(Integer.parseInt(id));
			}
			employeeService.deleteEmpBtach(listId);
		}else {
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
}
