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
	public List<Staff> findByDepartmentId(Integer id);
	public Staff findByIdAndDepartmentId(Integer staffId, Integer departmentId);
	public Staff findByFirstNameAndDepartmentId(String firstName, Integer departmentId);
	public Staff findByLastNameAndDepartmentId(String lastName, Integer departmentId);
	public Staff findByPhoneAndDepartmentId(String phone, Integer departmentId);
	public Staff findByEmailAndDepartmentId(String email, Integer departmentId);
	public Staff findByRegisteredTimeAndDepartmentId(Date registeredTime, Integer departmentId);
}
