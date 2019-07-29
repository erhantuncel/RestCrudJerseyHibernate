package com.erhan.rest.service;

import java.util.Date;
import java.util.List;

import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;

public interface StaffService {

	public void create(Staff staff);
	public void update(Staff staff);
	public void remove(Staff staff);
	public Staff findById(Integer id);
	public List<Staff> findAll();
	public List<Staff> findByFirstName(String firstName);
	public List<Staff> findByLastName(String LastName);
	public List<Staff> findByPhone(String phone);
	public List<Staff> findByEmail(String email);
	public List<Staff> findByRegisteredTime(Date registeredTime);
	public List<Staff> findByDepartment(Department department);
}
