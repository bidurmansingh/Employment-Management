package com.bidur.employeemanagement.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bidur.employeemanagement.dao.IEmployeeDAO;
import com.bidur.employeemanagement.domain.Employee;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addEmployee(Employee e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(e);
		logger.info("Employee saved successfully, Employee Details: " + e);
	}

	@Override
	public void updateEmployee(Employee e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(e);
		logger.info("Employee updated successfully, Employee Details: " + e);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee e = (Employee) session.load(Employee.class, new Long(id));
		logger.info("Employee loaded successfully, Employee Details: " + e);
		return e;
	}

	@Override
	public void removeEmployee(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee e = (Employee) session.load(Employee.class, new Long(id));
		if (e != null) {
			session.delete(e);
		}
		logger.info("Employee deleted successfully, Employee Details: " + e);
	}

	@Override
	public List<Employee> findAllEmployee() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employees = session.createQuery("FROM Employee").list();
		for (Employee e : employees) {
			logger.info("Employee List: " + e);
		}
		return employees;
	}
}
