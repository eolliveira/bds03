package com.devsuperior.bds03.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.bds03.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
