package com.bidur.employeemanagement.dao;

import java.util.List;

import com.bidur.employeemanagement.domain.Employee;

public interface IEmployeeDAO {

	public void addEmployee(Employee e);

	public void updateEmployee(Employee e);

	public Employee getEmployeeById(long id);

	public void removeEmployee(long id);

	public List<Employee> findAllEmployee();

}
