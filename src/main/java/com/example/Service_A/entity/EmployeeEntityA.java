package com.example.Service_A.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EmployeeEntityA {

	
	@Id
	private int employeeId;
	private String employeeName;
	private Double employeeSalary;
	private long employeePhoneNo;

}
