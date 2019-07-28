package com.erhan.rest.service;

import java.util.List;

import com.erhan.rest.dao.DepartmentDAO;
import com.erhan.rest.model.Department;

public class DepartmentService implements GenericService<Department> {

	private DepartmentDAO departmentDAO;

	public DepartmentService(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO; 
		departmentDAO.openSessionWithTransaction();
	}
	
	@Override
	public void create(Department entity) {
		departmentDAO.create(entity);
		departmentDAO.closeCurrentSessionWithTransaction();
	}

	@Override
	public void update(Department entity) {
		departmentDAO.update(entity);
		departmentDAO.closeCurrentSessionWithTransaction();
	}

	@Override
	public void remove(Department entity) {
		departmentDAO.remove(entity);
		departmentDAO.closeCurrentSessionWithTransaction();
	}

	@Override
	public Department findById(int id) {
		Department department = departmentDAO.findById(id);
		departmentDAO.closeCurrentSessionWithTransaction();
		return department;
	}

	@Override
	public List<Department> findAll() {
		List<Department> allDeparments = departmentDAO.findAll();
		departmentDAO.closeCurrentSessionWithTransaction();
		return allDeparments;
	}

	public Department findByDepartmentName(String name) {
		Department department = departmentDAO.findByDepartmentName(name);
		departmentDAO.closeCurrentSessionWithTransaction();
		return department;
	}
}
