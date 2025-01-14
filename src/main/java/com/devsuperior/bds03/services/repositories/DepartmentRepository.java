package com.devsuperior.bds03.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.bds03.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
