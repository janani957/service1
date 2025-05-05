package com.example.Service_A.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Service_A.entity.EmployeeEntityA;

public interface EmployeeRepositoryServiceA extends JpaRepository<EmployeeEntityA, Integer>{
}

