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
}
