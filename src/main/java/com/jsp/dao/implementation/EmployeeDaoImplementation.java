package com.jsp.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.jsp.dao.EmployeeDao;
import com.jsp.model.Employee;

public class EmployeeDaoImplementation implements EmployeeDao {
	static Connection connection = null;
	static {
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/employee_database_management?user=postgres&password=root");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean updateEmployeeNameById(int id, String newName) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update emp set name = ? where id = ?");
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updateEmployeeDesignationById(int id, String designation) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update emp set designation = ? where id = ?");

			preparedStatement.setString(1, designation);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeDepartmentNoById(int id, int deptNo) {
		// TODO Auto-generated method stub

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateEmployeeSalaryById(int id, double salary) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update emp set salary = ? where id = ?");
			preparedStatement.setDouble(1, salary);
			preparedStatement.setInt(2, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeNameByPhoneNo(long phone, String name) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeAddressByEmail(String email, String address) {
		// TODO Auto-generated method stub

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeDesignationByEmail(String email, String designation) {
		// TODO Auto-generated method stub

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeDesignationByDOB(String dob, String designation) {
		// TODO Auto-generated method stub

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from emp where id = ?");
			preparedStatement.setInt(1, id);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from emp where id = ?");
			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				System.out.println("----------------------------------------------");
				System.out.println("Employee ID : " + result.getInt(1));
				System.out.println("Employee Name : " + result.getString(2));
				System.out.println("Employee Email : " + result.getString(3));
				System.out.println("Employee Phone : " + result.getLong(4));
				System.out.println("Employee Gender : " + result.getString(5));
				System.out.println("Employee Dept No. : " + result.getInt(6));
				System.out.println("Employee Age : " + result.getInt(7));
				System.out.println("Employee Salary : " + result.getDouble(8));
				System.out.println("Employee Date of Birth : " + result.getString(9));
				System.out.println("Employee Designation : " + result.getString(10));
				System.out.println("Employee Address : " + result.getString(11));
				System.out.println("Employee Date of Joining : " + result.getDate(12));
				System.out.println("----------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmplyee() {
		// TODO Auto-generated method stub
		try {
			Statement statement = connection.createStatement();
			String query = "select * from emp";
			statement.execute(query);

			ResultSet result = statement.getResultSet();

			while (result.next()) {
				System.out.println("----------------------------------------------");
				System.out.println("Employee ID : " + result.getInt(1));
				System.out.println("Employee Name : " + result.getString(2));
				System.out.println("Employee Email : " + result.getString(3));
				System.out.println("Employee Phone : " + result.getLong(4));
				System.out.println("Employee Gender : " + result.getString(5));
				System.out.println("Employee Dept No. : " + result.getInt(6));
				System.out.println("Employee Age : " + result.getInt(7));
				System.out.println("Employee Salary : " + result.getDouble(8));
				System.out.println("Employee Date of Birth : " + result.getString(9));
				System.out.println("Employee Designation : " + result.getString(10));
				System.out.println("Employee Address : " + result.getString(11));
				System.out.println("Employee Date of Joining : " + result.getDate(12));
				System.out.println("----------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean sortEmployeeByNameAsc() {
		// TODO Auto-generated method stub
		try {
			Statement statement = connection.createStatement();
			String query = "select * from emp order by name asc";
			statement.execute(query);

			ResultSet result = statement.getResultSet();

			while (result.next()) {
				System.out.println("Employee ID : " + result.getInt(1));
				System.out.println("Employee Name : " + result.getString(2));
				System.out.println("Employee Email : " + result.getString(3));
				System.out.println("Employee Phone : " + result.getLong(4));
				System.out.println("Employee Gender : " + result.getString(5));
				System.out.println("Employee Dept No. : " + result.getInt(6));
				System.out.println("Employee Age : " + result.getInt(7));
				System.out.println("Employee Salary : " + result.getDouble(8));
				System.out.println("Employee Date of Birth : " + result.getString(9));
				System.out.println("Employee Designation : " + result.getString(10));
				System.out.println("Employee Address : " + result.getString(11));
				System.out.println("Employee Date of Joining : " + result.getDate(12));
				System.out.println("----------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean sortEmployeeByNameDesc() {
		// TODO Auto-generated method stub
		try {
			Statement statement = connection.createStatement();
			String query = "select * from emp order by name desc";
			statement.execute(query);

			ResultSet result = statement.getResultSet();

			while (result.next()) {
				System.out.println("----------------------------------------------");
				System.out.println("Employee ID : " + result.getInt(1));
				System.out.println("Employee Name : " + result.getString(2));
				System.out.println("Employee Email : " + result.getString(3));
				System.out.println("Employee Phone : " + result.getLong(4));
				System.out.println("Employee Gender : " + result.getString(5));
				System.out.println("Employee Dept No. : " + result.getInt(6));
				System.out.println("Employee Age : " + result.getInt(7));
				System.out.println("Employee Salary : " + result.getDouble(8));
				System.out.println("Employee Date of Birth : " + result.getString(9));
				System.out.println("Employee Designation : " + result.getString(10));
				System.out.println("Employee Address : " + result.getString(11));
				System.out.println("Employee Date of Joining : " + result.getDate(12));
				System.out.println("----------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean sortEmployeeByDateOfJoining() {
		// TODO Auto-generated method stub
		try {
			Statement statement = connection.createStatement();
			String query = "select * from emp order by date_of_joining";
			statement.execute(query);

			ResultSet result = statement.getResultSet();

			while (result.next()) {
				System.out.println("----------------------------------------------");
				System.out.println("Employee ID : " + result.getInt(1));
				System.out.println("Employee Name : " + result.getString(2));
				System.out.println("Employee Email : " + result.getString(3));
				System.out.println("Employee Phone : " + result.getLong(4));
				System.out.println("Employee Gender : " + result.getString(5));
				System.out.println("Employee Dept No. : " + result.getInt(6));
				System.out.println("Employee Age : " + result.getInt(7));
				System.out.println("Employee Salary : " + result.getDouble(8));
				System.out.println("Employee Date of Birth : " + result.getString(9));
				System.out.println("Employee Designation : " + result.getString(10));
				System.out.println("Employee Address : " + result.getString(11));
				System.out.println("Employee Date of Joining : " + result.getDate(12));
				System.out.println("----------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
