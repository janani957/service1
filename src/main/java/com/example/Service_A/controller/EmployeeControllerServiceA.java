package com.example.Service_A.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service_A.ServiceInter.EmployeeServiceAServiceInterface;
import com.example.Service_A.dto.EmployeeDtoA;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeControllerServiceA {
	
	@Autowired
	EmployeeServiceAServiceInterface employeeServiceAServiceInterface ;

	@PostMapping("/saveEmployeeA")
	public EmployeeDtoA saveEmployeeA(@RequestBody EmployeeDtoA employeeDtoA) {
        log.info("[SaveEmployeeA Method] - in Service_AController with Employee Dto - {}", employeeDtoA);
		EmployeeDtoA savedEmployeeDtoA = employeeServiceAServiceInterface.saveEmployeeA(employeeDtoA);
		log.info("Employee saved successfully: {}", savedEmployeeDtoA);
        return employeeDtoA;
	}
}
