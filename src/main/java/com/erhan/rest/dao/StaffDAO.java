package com.erhan.rest.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;

public interface StaffDAO extends JpaRepository<Staff, Integer> {

	public List<Staff> findByFirstName(String firstName);
	public List<Staff> findByLastName(String lastName);
	public List<Staff> findByPhone(String phone);
	public List<Staff> findByEmail(String email);
	public List<Staff> findByRegisteredTime(Date registeredTime);
	public List<Staff> findByDepartment(Department department);
	public List<Staff> findByDepartment_Id(Integer id);
	public List<Staff> findByIdAndDepartment_Id(Integer staffId, Integer departmentId);
	public List<Staff> findByFirstNameAndDepartment_Id(String firstName, Integer departmentId);
	public List<Staff> findByLastNameAndDepartment_Id(String lastName, Integer departmentId);
	public List<Staff> findByPhoneAndDepartment_Id(String phone, Integer departmentId);
	public List<Staff> findByEmailAndDepartment_Id(String email, Integer departmentId);
	public List<Staff> findByRegisteredTimeAndDepartment_Id(Date registeredTime, Integer departmentId);
	
}
