package com.jsp.customsorting;

import java.util.Comparator;

import com.jsp.model.Employee;

public class SortEmployeeByName implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		// TODO Auto-generated method stub
		return e1.getName().compareTo(e2.getName());
	}

}
