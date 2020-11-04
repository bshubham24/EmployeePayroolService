package com.capgi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.capgi.EmployeePayrollService.IOService;

public class EmployeePayrollServiceDBTest {
	@Test
	public void whenRecordsAreRetrievedFromEmpDatabaseShouldMatchTheEmpCount() throws EmployeePayrollException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		assertEquals(3, employeePayrollData.size());
	}

	@Test
	public void whenSalaryIsUpdated_ShouldMatchInDBAndList() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		empPayRollService.readEmployeePayrollData(IOService.DB_IO);
		empPayRollService.updateEmployeeSalary("anant", 20000);
		boolean result = empPayRollService.checkEmployeePayrollInSyncWithDB("anant");
		assertTrue(result);
	}

	@Test
	public void whenDataForDateRangeIsRetrieved_ShouldReturnEmpCount() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		LocalDate startDate = LocalDate.of(2020, 07, 16);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayrollData> empPayrollList = empPayRollService.getEmployeePayrollDataForDateRange(startDate,
				endDate);
		assertEquals(1, empPayrollList.size());
	}

	@Test
	public void givenDBFindSumOfSalaryOfMale_shouldReturnSum() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		List<EmployeePayrollData> empPayrollList = empPayRollService.getEmpDataGroupedByGender("salary", "SUM", "M");
		double sum = empPayrollList.get(0).getSalary();
		assertEquals(70000, sum, 0);
	}

	@Test
	public void givenDBFindAvgOfSalaryOfMale_shouldReturnSum() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		List<EmployeePayrollData> empPayrollList = empPayRollService.getEmpDataGroupedByGender("salary", "Avg", "M");
		double sum = empPayrollList.get(0).getSalary();
		assertEquals(35000, sum, 0);
	}

	@Test
	public void givenDBFindMaxOfSalaryOfMale_shouldReturnSum() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		List<EmployeePayrollData> empPayrollList = empPayRollService.getEmpDataGroupedByGender("salary", "MAX", "M");
		double sum = empPayrollList.get(0).getSalary();
		assertEquals(50000, sum, 0);
	}

	@Test
	public void givenDBFindMinOfSalaryOfFemale_shouldReturnSum() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		List<EmployeePayrollData> empPayrollList = empPayRollService.getEmpDataGroupedByGender("salary", "MIN", "F");
		String gender = empPayrollList.get(1).getGender();
		double sum = empPayrollList.get(1).getSalary();
		assertEquals(10000, sum, 0);
		assertEquals("F", gender);
	}

	@Test
	public void givenNewEmployee_WhenAdded_ShouldSyncWithDB() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		empPayRollService.readEmployeePayrollData(IOService.DB_IO);
		empPayRollService.addEmployeeToPayroll("tanya", 40000, LocalDate.now(), "F");
		boolean result = empPayRollService.checkEmployeePayrollInSyncWithDB("tanya");
		assertTrue(result);
	}
}
