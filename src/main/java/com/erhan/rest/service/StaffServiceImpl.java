package com.erhan.rest.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
		Staff staff = staffDAO.findById(id).orElse(null);
		if(staff == null) {
			throw new NotFoundException("Staff not found!");
		}
		return staff;
	}

	@Override
	public List<Staff> findAll() {
		return staffDAO.findAll();
	}

	@Override
	public List<Staff> findAllPaginated(int page, int pageSize) {
		List<Staff> allStaff = staffDAO.findAll(PageRequest.of(page, pageSize)).getContent();
		if(allStaff.size() <= 0) {
			throw new NotFoundException("Staff list not found!");
		}
		return allStaff;
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
		List<Staff> staffList = staffDAO.findByDepartment_Id(id);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff list with department Id " + id + " not found!");
		}
		return staffList;
	}

	@Override
	public List<Staff> findByDepartmentIdPaginated(Integer id, int page, int pageSize) {
		List<Staff> staffList = staffDAO.findByDepartment_Id(id, PageRequest.of(page, pageSize));
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff list not found!");
		}
		return staffList;
	}
	
	@Override
	public Staff findByIdAndDepartmentId(Integer staffId, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByIdAndDepartment_Id(staffId, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with Id = " + staffId + " not found!");
		}
		return staffList.get(0);
	}

	@Override
	public Staff findByFirstNameAndDepartmentId(String firstName, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByFirstNameAndDepartment_Id(firstName, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with first name = " + firstName + " not found!");
		}
		return staffList.get(0);
	}
	
	@Override
	public List<Staff> findByFirstNameAndDepartmentIdPaginated(String firstName, Integer departmentId, int page, int pageSize) {
		List<Staff> staffList = staffDAO.findByFirstNameAndDepartment_Id(firstName, departmentId, PageRequest.of(page, pageSize));
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff list not found!");
		}
		return staffList;
	}

	@Override
	public Staff findByLastNameAndDepartmentId(String lastName, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByLastNameAndDepartment_Id(lastName, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with last name = " + lastName + " not found!");
		}
		return staffList.get(0);
	}

	@Override
	public List<Staff> findByLastNameAndDepartmentIdPaginated(String lastName, Integer departmentId, int page, int pageSize) {
		List<Staff> staffList = staffDAO.findByLastNameAndDepartment_Id(lastName, departmentId, PageRequest.of(page, pageSize));
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff list not found!");
		}
		return staffList;
	}
	
	@Override
	public Staff findByPhoneAndDepartmentId(String phone, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByPhoneAndDepartment_Id(phone, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with phone number = " + phone + " not found!");
		}
		return staffList.get(0);
	}

	@Override
	public Staff findByEmailAndDepartmentId(String email, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByEmailAndDepartment_Id(email, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with email = " + email + " not found!");
		}
		return staffList.get(0);
	}

	@Override
	public Staff findByRegisteredTimeAndDepartmentId(Date registeredTime, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByRegisteredTimeAndDepartment_Id(registeredTime, departmentId);
		if(staffList.size() <= 0) {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			throw new NotFoundException("Staff with registered time = " + df.format(registeredTime) + " not found!");
		}
		return staffList.get(0);
	}
}
