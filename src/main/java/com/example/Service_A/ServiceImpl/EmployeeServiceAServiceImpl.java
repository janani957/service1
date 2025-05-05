package com.example.Service_A.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.Service_A.ServiceInter.EmployeeServiceAServiceInterface;
import com.example.Service_A.dto.EmployeeDtoA;
import com.example.Service_A.entity.EmployeeEntityA;
import com.example.Service_A.repository.EmployeeRepositoryServiceA;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceAServiceImpl implements EmployeeServiceAServiceInterface {
	
	
	@Autowired
	EmployeeRepositoryServiceA employeeRepositoryServiceA;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public EmployeeDtoA saveEmployeeA(EmployeeDtoA employeeDtoA) {
        log.info("Starting Employee save operation for: {}", employeeDtoA);
		 employeeDtoA =  convertEntityToDto(employeeRepositoryServiceA.save(convertDtoToEntity(employeeDtoA)));
        log.info("Employee entity saved to database: {}", employeeDtoA);
		try {
			String Key=String.valueOf(employeeDtoA.getEmployeeId());
			kafkaTemplate.send("Employee-Service",Key,objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeDtoA));
            log.info("Published Employee to Kafka with key {}: {}", Key, employeeDtoA);
		}catch (Exception e) {
			e.printStackTrace();
		}
        log.info("Completed Employee save operation for: {}", employeeDtoA);
		return employeeDtoA;
	}
	
	public EmployeeDtoA convertEntityToDto(EmployeeEntityA employeeEntityA) {
		return modelMapper.map(employeeEntityA, EmployeeDtoA.class);
	}
	public EmployeeEntityA convertDtoToEntity(EmployeeDtoA employeeDtoA) {
		return modelMapper.map(employeeDtoA, EmployeeEntityA.class);
	}


}
