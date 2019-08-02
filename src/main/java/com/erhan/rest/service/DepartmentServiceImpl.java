package com.erhan.rest.service;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	public void removeById(Integer id) {
		departmentDAO.deleteById(id);
	}

	@Override
	public Department findById(Integer id) {
		Department department = departmentDAO.findById(id).orElse(null);
		if(department == null) {
			throw new NotFoundException("Department not found!");
		}
		return department;
	}

	@Override
	public List<Department> findAll() {
		return departmentDAO.findAll();
	}

	public Department findByDepartmentName(String name) {
		Department department = departmentDAO.findFirstByDepartmentName(name);
		if(department == null) {
			throw new NotFoundException("Department not found!");
		}
		return department;
	}

	@Override
	public List<Department> findAllPaginated(int page, int pageSize) {
		return departmentDAO.findAll(PageRequest.of(page, pageSize)).getContent();
	}
}
