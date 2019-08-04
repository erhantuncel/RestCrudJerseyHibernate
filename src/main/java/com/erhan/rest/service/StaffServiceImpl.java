package com.erhan.rest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.rest.dao.DepartmentDAO;
import com.erhan.rest.dao.StaffDAO;
import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	
	@Autowired
	private StaffDAO staffDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Override
	public void create(Staff entity) {
		staffDAO.save(entity);
	}

	@Override
	public void createWithDepartmentId(Staff staff, Integer departmentId) {
		Department department = departmentDAO.findById(departmentId).orElse(null);
		if(department == null) {
			throw new NotFoundException("Department with id = " + departmentId + " not found!");
		}
		staff.setDepartment(department);
		staffDAO.save(staff);
	}
	
	@Override
	public void update(Staff entity) {
		staffDAO.save(entity);
	}

	@Override
	public void updateWithDepartmentId(Integer staffId, Integer departmentId, Staff staff) {
		Department department = departmentDAO.findById(departmentId).orElse(null);
		if(department == null) {
			throw new NotFoundException("Department with id = " + departmentId + " not found!");
		}
		Staff staffFromDb = staffDAO.findById(staffId).orElse(null);
		if(staffFromDb == null) {
			throw new NotFoundException("Staff with id = " + staffId + " not found!");
		}
		if(!department.getStaffList().contains(staffFromDb)) {
			throw new BadRequestException("Department with id = " + departmentId + 
					" does not have staff with id = " + staffId);
			
		}
		staffFromDb.setPhone(staff.getPhone());
		staffFromDb.setEmail(staff.getEmail());
		staffDAO.save(staffFromDb);
	}
	
	@Override
	public void remove(Staff entity) {
		staffDAO.delete(entity);
	}

	@Override
	public void removeWithDepartmentId(Integer staffId, Integer departmentId) {
		Department department = departmentDAO.findById(departmentId).orElse(null);
		if(department == null) {
			throw new NotFoundException("Department with id = " + departmentId + " not found!");
		}
		Staff staffFromDb = staffDAO.findById(staffId).orElse(null);
		if(staffFromDb == null) {
			throw new NotFoundException("Staff with id = " + staffId + " not found!");
		}
		if(!department.getStaffList().contains(staffFromDb)) {
			throw new BadRequestException("Department with id = " + departmentId + 
					" does not have staff with id = " + staffId);	
		}
		department.getStaffList().remove(staffFromDb);
		departmentDAO.save(department);
		staffDAO.delete(staffFromDb);
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
	public List<Staff> findByFirstNameAndDepartmentId(String firstName, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByFirstNameAndDepartment_Id(firstName, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with first name = " + firstName + " not found!");
		}
		return staffList;
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
	public List<Staff> findByLastNameAndDepartmentId(String lastName, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByLastNameAndDepartment_Id(lastName, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with last name = " + lastName + " not found!");
		}
		return staffList;
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
	public List<Staff> findByPhoneAndDepartmentId(String phone, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByPhoneAndDepartment_Id(phone, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with phone number = " + phone + " not found!");
		}
		return staffList;
	}

	@Override
	public List<Staff> findByEmailAndDepartmentId(String email, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByEmailAndDepartment_Id(email, departmentId);
		if(staffList.size() <= 0) {
			throw new NotFoundException("Staff with email = " + email + " not found!");
		}
		return staffList;
	}

	@Override
	public List<Staff> findByRegisteredTimeAndDepartmentId(Date registeredTime, Integer departmentId) {
		List<Staff> staffList = staffDAO.findByRegisteredTimeAndDepartment_Id(registeredTime, departmentId);
		if(staffList.size() <= 0) {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			throw new NotFoundException("Staff with registered time = " + df.format(registeredTime) + " not found!");
		}
		return staffList;
	}

	@Override
	public List<Staff> findByDepartmentIdAndQueryParameters(Integer departmentId,
			MultivaluedMap<String, String> queryParameters) throws ParseException {
		Set<String> keySet = queryParameters.keySet();
		Integer page = null;
		Integer pageSize = null;
		switch (queryParameters.size()) {
		case 1:
			if(keySet.contains("firstName")) {
				return findByFirstNameAndDepartmentId(queryParameters.getFirst("firstName"), departmentId);
			} 
			if(keySet.contains("lastName")) {
				return findByLastNameAndDepartmentId(queryParameters.getFirst("lastName"), departmentId);
			}
			if(keySet.contains("phone")) {
				return findByPhoneAndDepartmentId(queryParameters.getFirst("phone"), departmentId);
			}
			if(keySet.contains("email")) {
				return findByEmailAndDepartmentId(queryParameters.getFirst("email"), departmentId);
			}
			if(keySet.contains("registeredTime")) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.forLanguageTag("tr"));
				return findByRegisteredTimeAndDepartmentId(df.parse(queryParameters.getFirst("registeredTime")), departmentId);
			}
			throw new BadRequestException("Wrong query filter");

		case 2 :
			if(!keySet.contains("page") || !keySet.contains("size")) {
				throw new BadRequestException("Wrong query filter!");
			}
			page = Integer.valueOf(queryParameters.getFirst("page"));
			pageSize = Integer.valueOf(queryParameters.getFirst("size"));
			return findByDepartmentIdPaginated(departmentId, page, pageSize);
		case 3 :
			if(keySet.contains("page") && keySet.contains("size")) {
				page = Integer.valueOf(queryParameters.getFirst("page"));
				pageSize = Integer.valueOf(queryParameters.getFirst("size"));
				if(keySet.contains("firstName")) {
					return findByFirstNameAndDepartmentIdPaginated(queryParameters.getFirst("firstName"), departmentId, 
							page, pageSize);
				} 
				if(keySet.contains("lastName")) {
					return findByLastNameAndDepartmentIdPaginated(queryParameters.getFirst("lastName"), departmentId,
							page, pageSize);
				}
				throw new BadRequestException("Wrong query filter");
			} else {				
				throw new BadRequestException("Too much query filter!");
			}
			
		default :
			throw new BadRequestException("Too much query parameters!");
		}
	}

	

	

	
	
}
