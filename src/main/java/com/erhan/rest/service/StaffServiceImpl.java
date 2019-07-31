package com.erhan.rest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.rest.dao.StaffDAO;
import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDAO staffDAO;
	
	@Override
	public void create(Staff entity) {
		staffDAO.save(entity);
	}

	@Override
	public void update(Staff entity) {
		staffDAO.save(entity);
	}

	@Override
	public void remove(Staff entity) {
		staffDAO.delete(entity);
	}

	@Override
	public Staff findById(Integer id) {
		return staffDAO.findById(id).orElse(null);
	}

	@Override
	public List<Staff> findAll() {
		return staffDAO.findAll();
	}

	public List<Staff> findByFirstName(String firstName) {
		return staffDAO.findByFirstName(firstName);
	}
	
	public List<Staff> findByLastName(String lastName) {
		return staffDAO.findByLastName(lastName);
	}
	
	public List<Staff> findByPhone(String phone) {
		return staffDAO.findByPhone(phone);
	}
	
	public List<Staff> findByEmail(String email) {
		return staffDAO.findByEmail(email);
	}
	
	public List<Staff> findByRegisteredTime(Date registeredTime) {
		return staffDAO.findByRegisteredTime(registeredTime);
	}

	@Override
	public List<Staff> findByDepartment(Department department) {
		return staffDAO.findByDepartment(department);
	}

	@Override
	public List<Staff> findByDepartmentId(Integer id) {
		return staffDAO.findByDepartment_Id(id);
	}
}
