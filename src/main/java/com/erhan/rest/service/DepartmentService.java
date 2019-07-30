package com.erhan.rest.service;

import java.util.List;

import com.erhan.rest.model.Department;

public interface DepartmentService {

	public void create(Department department);
	public void update(Department department);
	public void remove(Department department);
	public void removeById(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
	public Department findByDepartmentName(String name);
}
