package com.jsp.dao;

import java.util.List;

import com.jsp.model.Employee;

public interface EmployeeDao {

	public boolean saveEmployee(Employee employee);

	public boolean updateEmployeeNameById(int id, String newName);

	public boolean updateEmployeeDesignationById(int id, String designation);

	public boolean updateEmployeeDepartmentNoById(int id, int deptNo);

	public boolean updateEmployeeSalaryById(int id, double salary);

	public boolean updateEmployeeNameByPhoneNo(long phone, String name);

	public boolean updateEmployeeAddressByEmail(String email, String address);

	public boolean updateEmployeeDesignationByEmail(String email, String designation);

	public boolean updateEmployeeDesignationByDOB(String dob, String designation);

	public boolean deleteEmployeeById(int id);

	Employee getEmployeeById(int id);

	List<Employee> getAllEmplyee();

	public boolean sortEmployeeByNameAsc();

	public boolean sortEmployeeByNameDesc();

	public boolean sortEmployeeByDateOfJoining();

}
