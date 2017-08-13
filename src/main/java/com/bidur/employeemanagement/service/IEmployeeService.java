package com.bidur.employeemanagement.service;

import java.util.List;

import com.bidur.employeemanagement.domain.Employee;

public interface IEmployeeService {

	public void addEmployee(Employee e);

	public void updateEmployee(Employee e);

	public List<Employee> findAll();

	public Employee getEmployeeById(long id);

	public void removeEmployee(long id);
}
