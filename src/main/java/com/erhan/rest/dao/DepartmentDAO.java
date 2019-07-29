package com.erhan.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erhan.rest.model.Department;

public interface DepartmentDAO extends JpaRepository<Department, Integer> {

	public Department findFirstByDepartmentName(String name);
}
