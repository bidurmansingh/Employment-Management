package com.bidur.employeemanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidur.employeemanagement.dao.IEmployeeDAO;
import com.bidur.employeemanagement.domain.Employee;
import com.bidur.employeemanagement.service.IEmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDAO employeeDAO;

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public void addEmployee(Employee e) {
		employeeDAO.addEmployee(e);

	}

	@Override
	public void updateEmployee(Employee e) {
		employeeDAO.updateEmployee(e);

	}

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAllEmployee();
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeDAO.getEmployeeById(id);
	}

	@Override
	public void removeEmployee(long id) {
		employeeDAO.removeEmployee(id);
	}
}
