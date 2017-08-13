package com.bidur.employeemanagement.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bidur.employeemanagement.domain.Employee;
import com.bidur.employeemanagement.service.IEmployeeService;

@Controller
public class EmployeeRestController {

	private IEmployeeService employeeService;

	@Autowired(required = true)
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value="/api/getAllEmployees", method=RequestMethod.GET)
	public @ResponseBody
	List<Employee> getAllEmployees(){
			return employeeService.findAll();
	}

}
