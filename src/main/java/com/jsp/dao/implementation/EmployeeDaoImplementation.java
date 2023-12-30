package com.jsp.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsp.customsorting.SortEmployeeByDateOfJoining;
import com.jsp.customsorting.SortEmployeeByName;
import com.jsp.dao.EmployeeDao;
import com.jsp.model.Employee;
import com.jsp.model.Gender;

public class EmployeeDaoImplementation implements EmployeeDao {
	static Connection connection;
	static {
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/employee_database_management?user=postgres&password=root");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveEmployee(Employee employee) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into emp values (?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setLong(4, employee.getPhone());
			preparedStatement.setString(5, employee.getGender().toString());
			preparedStatement.setInt(6, employee.getDeptNo());
			preparedStatement.setInt(7, employee.getAge());
			preparedStatement.setDouble(8, employee.getSalary());
			preparedStatement.setString(9, employee.getDateOfBirth());
			preparedStatement.setString(10, employee.getDesignation());
			preparedStatement.setString(11, employee.getAddress());
			preparedStatement.setDate(12, employee.getDateOfJoining());

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean updateEmployeeNameById(int id, String newName) {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update emp set name = ? where id = ?");
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updateEmployeeDesignationById(int id, String designation) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set designation = ? where id = ?");

			preparedStatement.setString(1, designation);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeDepartmentNoById(int id, int deptNo) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set dept_no = ? where id = ?");
			preparedStatement.setInt(1, deptNo);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeSalaryById(int id, double salary) {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update emp set salary = ? where id = ?");
			preparedStatement.setDouble(1, salary);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeNameByPhoneNo(long phone, String name) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set name = ? where phone = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setLong(2, phone);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeAddressByEmail(String email, String address) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set address = ? where email = ?");
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, email);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeDesignationByEmail(String email, String designation) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set designation = ? where email = ?");
			preparedStatement.setString(1, designation);
			preparedStatement.setString(2, email);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeDesignationByDOB(String dob, String designation) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set designation = ? where date_of_birth = ?");
			preparedStatement.setString(1, designation);
			preparedStatement.setString(2, dob);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from emp where id = ?");
			preparedStatement.setInt(1, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Employee getEmployeeById(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from emp where id = ?");
			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			Employee employee = new Employee();

			if (result != null)
				while (result.next()) {

					int idRetrieved = result.getInt(1);
					employee.setId(id);
					employee.setName(result.getString(2));
					employee.setEmail(result.getString(3));
					employee.setPhone(result.getLong(4));

					String gen = result.getString(5);
					employee.setGender(Gender.valueOf(gen));

					employee.setDeptNo(result.getInt(6));
					employee.setAge(result.getInt(7));
					employee.setSalary(result.getDouble(8));
					employee.setDateOfBirth(result.getString(9));
					employee.setDesignation(result.getString(10));
					employee.setAddress(result.getString(11));

					employee.setDateOfJoining(result.getDate(12));

					return employee;

				}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employeeList = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from emp");
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Employee employee = new Employee();

				employee.setId(result.getInt(1));
				employee.setName(result.getString(2));
				employee.setEmail(result.getString(3));
				employee.setPhone(result.getLong(4));

				String gen = result.getString(5);
				employee.setGender(Gender.valueOf(gen));

				employee.setDeptNo(result.getInt(6));
				employee.setAge(result.getInt(7));
				employee.setSalary(result.getDouble(8));
				employee.setDateOfBirth(result.getString(9));
				employee.setDesignation(result.getString(10));
				employee.setAddress(result.getString(11));

				employee.setDateOfJoining(result.getDate(12));

				employeeList.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public List<Employee> sortEmployeeByNameAsc() {

		List<Employee> employees = getAllEmployee();

		Collections.sort(employees, new SortEmployeeByName());

		return employees;
	}

	@Override
	public List<Employee> sortEmployeeByDateOfJoining() {

		List<Employee> employees = getAllEmployee();

		Collections.sort(employees, new SortEmployeeByDateOfJoining());

		return employees;
	}

}
