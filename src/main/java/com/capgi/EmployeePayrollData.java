package com.capgi;

import java.time.LocalDate;

public class EmployeePayrollData {
	private int id;
	private String name;
	private double salary;
	private LocalDate date;
	private String gender;

	public EmployeePayrollData(Integer id, String name, Double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public EmployeePayrollData(Integer id, String name, Double salary, LocalDate date) {
		this(id, name, salary);
		this.date = date;
	}

	public EmployeePayrollData(Integer id, String name, Double salary, LocalDate date, String gender) {
		this(id, name, salary, date);
		this.gender = gender;
	}

	public EmployeePayrollData(String gender, Double salary) {
		this.gender = gender;
		this.salary = salary;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmployeePayrollData that = (EmployeePayrollData) o;
		return id == that.id && Double.compare(that.salary, salary) == 0 && name.equals(that.name);
	}

	@Override
	public String toString() {
		return "id= " + id + ", name= " + name + ", salary= " + salary;
	}
}
