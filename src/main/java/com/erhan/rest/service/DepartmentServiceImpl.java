package com.erhan.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.rest.dao.DepartmentDAO;
import com.erhan.rest.model.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Override
	public void create(Department entity) {
		departmentDAO.save(entity);
	}

	@Override
	public void update(Department entity) {
		departmentDAO.save(entity);
	}

	@Override
	public void remove(Department entity) {
		departmentDAO.delete(entity);
	}

	@Override
	public Department findById(Integer id) {
		return departmentDAO.findById(id).orElse(null);
	}

	@Override
	public List<Department> findAll() {
		return departmentDAO.findAll();
	}

	public Department findByDepartmentName(String name) {
		return departmentDAO.findFirstByDepartmentName(name);
	}
}
