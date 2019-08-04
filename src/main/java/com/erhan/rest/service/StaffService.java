package com.erhan.rest.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;

public interface StaffService {

	public void create(Staff staff);
	public void createWithDepartmentId(Staff staff, Integer departmentId);
	public void update(Staff staff);
	public void updateWithDepartmentId(Integer staffId, Integer departmentId, Staff staff);
	public void remove(Staff staff);
	public Staff findById(Integer id);
	public List<Staff> findAll();
	public List<Staff> findAllPaginated(int page, int pageSize);
	public List<Staff> findByFirstName(String firstName);
	public List<Staff> findByLastName(String LastName);
	public List<Staff> findByPhone(String phone);
	public List<Staff> findByEmail(String email);
	public List<Staff> findByRegisteredTime(Date registeredTime);
	public List<Staff> findByDepartment(Department department);
	public List<Staff> findByDepartmentId(Integer id);
	public List<Staff> findByDepartmentIdPaginated(Integer id, int page, int pageSize);
	public Staff findByIdAndDepartmentId(Integer staffId, Integer departmentId);
	public List<Staff> findByFirstNameAndDepartmentId(String firstName, Integer departmentId);
	public List<Staff> findByFirstNameAndDepartmentIdPaginated(String firstName, Integer departmentId, int page, int pageSize);
	public List<Staff> findByLastNameAndDepartmentId(String lastName, Integer departmentId);
	public List<Staff> findByLastNameAndDepartmentIdPaginated(String lastName, Integer departmentId, int page, int pageSize);
	public List<Staff> findByPhoneAndDepartmentId(String phone, Integer departmentId);
	public List<Staff> findByEmailAndDepartmentId(String email, Integer departmentId);
	public List<Staff> findByRegisteredTimeAndDepartmentId(Date registeredTime, Integer departmentId);
	public List<Staff> findByDepartmentIdAndQueryParameters(Integer departmentId, MultivaluedMap<String, String> queryParameters) throws ParseException; 
}
