package com.jsp.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.jsp.dao.EmployeeDao;
import com.jsp.dao.implementation.EmployeeDaoImplementation;
import com.jsp.model.Employee;
import com.jsp.model.Gender;

public class MainClass {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		while (true) {
			EmployeeDao dao = new EmployeeDaoImplementation();

			System.out.println("\nWELCOME TO EMPLOYEE DATABASE");
			System.out.println("1.  Add Employee");
			System.out.println("2.  Update Employee Name By ID");
			System.out.println("3.  Update Employee Designation By ID");
			System.out.println("4.  Update Employee Department Number By ID");
			System.out.println("5.  Update Employee Salary By ID");
			System.out.println("6.  Update Employee Name using Phone Number");
			System.out.println("7.  Update Employee Address using Email");
			System.out.println("8.  Update Employee Designation using Email");
			System.out.println("9.  Update Employee Designation using Date of Birth");
			System.out.println("10. Delete Employee using ID");
			System.out.println("11. Get Employee by Id");
			System.out.println("12. Get Employee - All Employee Record");
			System.out.println("13. Sort Employee By Name");
			System.out.println("14. Sort Employee By Date Of Joining");
			System.out.println("15. Exit Program");
			System.out.println("\nEnter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				Employee employee = createEmployee();
				boolean result = dao.saveEmployee(employee);
				System.out.println("\nData Inserted Successfully...");
				break;

			case 2:
				System.out.println("Enter ID to be Updated : ");
				int id = sc.nextInt();
				System.out.println("Enter the New Name : ");
				sc.nextLine();
				String name = sc.nextLine();
				dao.updateEmployeeNameById(id, name);
				System.out.println("\nUpdated Successfully...");
				break;

			case 3:
				System.out.println("Enter the ID to be updated :");
				id = sc.nextInt();
				System.out.println("Enter the new Designation :");
				sc.nextLine();
				String designation = sc.nextLine();
				dao.updateEmployeeDesignationById(id, designation);
				System.out.println("\nUpdated Successfully...");
				break;

			case 4:
				System.out.println("Enter ID to be Updated :");
				id = sc.nextInt();
				System.out.println("Enter the New Department Number :");
				int newDeptNo = sc.nextInt();
				dao.updateEmployeeDepartmentNoById(id, newDeptNo);
				System.out.println("\nUpdated Successfully...");
				break;

			case 5:
				System.out.println("Enter ID to be Updated :");
				id = sc.nextInt();
				System.out.println("Enter the New Salary :");
				double salary = sc.nextDouble();
				dao.updateEmployeeSalaryById(id, salary);
				System.out.println("\nUpdated Successfully...");
				break;

			case 6:
				System.out.println("Enter Phone No to be Updated :");
				long phone = sc.nextLong();
				System.out.println("Enter the New Name :");
				sc.nextLine();
				name = sc.nextLine();
				dao.updateEmployeeNameByPhoneNo(phone, name);
				System.out.println("\nUpdated Successfully...");
				break;

			case 7:
				System.out.println("Enter Email to be Updated :");
				sc.nextLine();
				String email = sc.nextLine();
				System.out.println("Enter the New Address :");
				String address = sc.nextLine();
				dao.updateEmployeeAddressByEmail(email, address);
				System.out.println("\nUpdated Successfully...");
				break;

			case 8:
				System.out.println("Enter Email to be Updated :");
				sc.nextLine();
				email = sc.nextLine();
				System.out.println("Enter the New Designation :");
				designation = sc.nextLine();
				dao.updateEmployeeDesignationByEmail(email, designation);
				System.out.println("\nUpdated Successfully...");
				break;

			case 9:
				System.out.println("Enter Date of Birth to be Updated :");
				sc.nextLine();
				String dob = sc.nextLine();
				System.out.println("Enter the New Designation :");
				designation = sc.nextLine();
				dao.updateEmployeeDesignationByDOB(dob, designation);
				System.out.println("\nUpdated Successfully...");
				break;

			case 10:
				System.out.println("Enter an ID to be Deleted :");
				id = sc.nextInt();
				dao.deleteEmployeeById(id);
				System.out.println("Deleted Successfully...");
				break;

			case 11:
				System.out.println("Enter the ID :");
				id = sc.nextInt();
				dao.getEmployeeById(id);
				break;

			case 12:
				dao.getAllEmplyee();
				break;

			case 13:
				System.out.println("1. Sort Name in Ascending");
				System.out.println("2. Sort Name in Descending");
				int click = sc.nextInt();

				if (click == 1)
					dao.sortEmployeeByNameAsc();
				else if (click == 2)
					dao.sortEmployeeByNameDesc();
				else
					System.out.println("Invalid Input...");
				break;

			case 14:
				dao.sortEmployeeByDateOfJoining();
				break;

			case 15:
				System.out.println("* * * * * E X I T * * * * *");
				System.exit(0);

			default:
				break;
			}
		}
	}

	private static Employee createEmployee() {

		Employee employee = new Employee();

		System.out.println("Enter Employee ID : ");
		employee.setId(sc.nextInt());
		System.out.println("Enter Employee Name : ");
		employee.setName(sc.next());
		System.out.println("Enter Employee Email : ");
		employee.setEmail(sc.next());
		System.out.println("Enter Employee Phone Number : ");
		employee.setPhone(sc.nextLong());
		System.out.println("Select Gender");
		System.out.println(" 1. Male \n 2. Female \n 3. Others");
		int genderChoice = sc.nextInt();

		switch (genderChoice) {
		case 1:
			employee.setGender(Gender.MALE);
			System.out.println("selected MALE");
			break;

		case 2:
			employee.setGender(Gender.FEMALE);
			System.out.println("selected FEMALE");
			break;

		default:
			employee.setGender(Gender.OTHERS);
			System.out.println("selected OTHERS");
			break;
		}

		System.out.println("Enter Employee Department Number : ");
		employee.setDeptNo(sc.nextInt());
		System.out.println("Enter Employee Age");
		employee.setAge(sc.nextInt());
		System.out.println("Enter Employee Salary");
		employee.setSalary(sc.nextDouble());
		System.out.println("Enter Employee Date Of Birth in DD-MM-YYYY");
		sc.nextLine();
		employee.setDateOfBirth(sc.nextLine());
		System.out.println("Enter Employee Designation");
		employee.setDesignation(sc.nextLine());
		System.out.println("Enter Employee Address");
		employee.setAddress(sc.nextLine());
		System.out.println("Enter Employee Date Of Joining in DD-MM-YYYY");
		String date = sc.nextLine();

		SimpleDateFormat s1 = new SimpleDateFormat("dd-MM-yyyy");
		Date d;
		try {
			d = s1.parse(date);
			employee.setDateOfJoining(new java.sql.Date(d.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
}
