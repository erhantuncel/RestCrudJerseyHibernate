package com.erhan.rest.service;

import java.util.Date;
import java.util.List;

import com.erhan.rest.dao.StaffDAO;
import com.erhan.rest.model.Staff;

public class StaffService implements GenericService<Staff> {

	private StaffDAO staffDAO;
	
	public StaffService(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
		staffDAO.openSessionWithTransaction();
	}
	
	@Override
	public void create(Staff entity) {
		staffDAO.create(entity);
		staffDAO.closeCurrentSessionWithTransaction();
	}

	@Override
	public void update(Staff entity) {
		staffDAO.update(entity);
		staffDAO.closeCurrentSessionWithTransaction();
	}

	@Override
	public void remove(Staff entity) {
		staffDAO.remove(entity);
		staffDAO.closeCurrentSessionWithTransaction();
	}

	@Override
	public Staff findById(int id) {
		Staff staff = staffDAO.findById(id);
		staffDAO.closeCurrentSessionWithTransaction();
		return staff;
	}

	@Override
	public List<Staff> findAll() {
		List<Staff> allStaffs = staffDAO.findAll();
		staffDAO.closeCurrentSessionWithTransaction();
		return allStaffs;
	}

	public List<Staff> findByFirstName(String firstName) {
		List<Staff> staffList = staffDAO.findByFirtsName(firstName);
		staffDAO.closeCurrentSessionWithTransaction();
		return staffList;
	}
	
	public List<Staff> findByLastName(String lastName) {
		List<Staff> staffs = staffDAO.findByLastName(lastName);
		staffDAO.closeCurrentSessionWithTransaction();
		return staffs;
	}
	
	public List<Staff> findByPhone(String phone) {
		List<Staff> staffs = staffDAO.findByPhone(phone);
		staffDAO.closeCurrentSessionWithTransaction();
		return staffs;
	}
	
	public List<Staff> findByEmail(String email) {
		List<Staff> staffs = staffDAO.findByEmail(email);
		staffDAO.closeCurrentSessionWithTransaction();
		return staffs;
	}
	
	public List<Staff> findByRegisteredTime(Date registeredTime) {
		List<Staff> staffs = staffDAO.findByRegisteredTime(registeredTime);
		staffDAO.closeCurrentSessionWithTransaction();
		return staffs;
	}

}
