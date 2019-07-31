package com.erhan.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.erhan.rest.dao.StaffDAO;
import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;


@RunWith(MockitoJUnitRunner.class)
public class StaffServiceTest {
	
	public static final Logger logger = LogManager.getLogger(StaffServiceTest.class);
	
	@Mock
	StaffDAO mockStaffDAO;
	
	@InjectMocks
	StaffServiceImpl staffService;
	
	private Staff staff;
	private Department department;
	private List<Staff> staffList;
	
	@Before
	public void setUp() {
		department = new Department("Üretim");
		staff = new Staff("Mehmet", "ÇALIŞKAN", "1231231231", "mehmet@abc.com", new Date(), department);
		staffList = new ArrayList<Staff>();
		staffList.add(staff);
		staffList.add(new Staff("Ahmet", "TEMBEL", "9879879879", "ahmet@abc.com", new Date(), department));
	}

	@Test
	public void testCreate() {
		logger.info("testCreate is started.");
		
		staffService.create(staff);
		
		verify(mockStaffDAO, times(1)).save(any(Staff.class));
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testUpdate() {
		logger.info("testUpdate is started.");
		
		staffService.update(staff);
		
		verify(mockStaffDAO, times(1)).save(any(Staff.class));
		
		logger.info("testUpdate is successful.");
	}
	
	@Test
	public void testRemove() {
		logger.info("testRemove is started.");
		
		staffService.remove(staff);
		
		verify(mockStaffDAO, times(1)).delete(any(Staff.class));
		
		logger.info("testRemove is successful.");
	}
	
	@Test
	public void testFindById() {
		logger.info("testFindById is started.");
		
		when(mockStaffDAO.findById(2)).thenReturn(Optional.of(staff));
		
		Staff findById = staffService.findById(2);
		assertNotNull(findById);
		assertEquals(findById.getFirstName(), staff.getFirstName());
		assertEquals(findById.getLastName(), staff.getLastName());
		assertEquals(findById.getPhone(), staff.getPhone());
		assertEquals(findById.getEmail(), staff.getEmail());
		assertEquals(findById.getRegisteredTime(), staff.getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findById(anyInt());
		
		logger.info("testFindById is successful.");
	}
	
	@Test
	public void testFindAll() {
		logger.info("testFindAll is started.");
		
		when(mockStaffDAO.findAll()).thenReturn(staffList);
		
		List<Staff> allStaffs = staffService.findAll();
		assertNotNull(allStaffs);
		assertEquals(allStaffs.size(), 2);
		assertEquals(allStaffs.get(0).getFirstName(), staff.getFirstName());
		assertEquals(allStaffs.get(0).getLastName(), staff.getLastName());
		assertEquals(allStaffs.get(0).getPhone(), staff.getPhone());
		assertEquals(allStaffs.get(0).getEmail(), staff.getEmail());
		assertEquals(allStaffs.get(0).getRegisteredTime(), staff.getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findAll();
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	public void testFindByFirstName() {
		logger.info("testFindByFirstName is started.");
		
		when(mockStaffDAO.findByFirstName(anyString())).thenReturn(staffList);
		
		List<Staff> findByFirstName = staffService.findByFirstName("Ahmet");
		assertNotNull(findByFirstName);
		assertEquals(findByFirstName.size(), 2);
		assertEquals(findByFirstName.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByFirstName.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByFirstName.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByFirstName.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByFirstName.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findByFirstName(anyString());
		
		logger.info("testFindByFirstName is successful.");
	}
	
	@Test
	public void testFindByLastName() {
		logger.info("testFindByLastName is started.");
		
		when(mockStaffDAO.findByLastName(anyString())).thenReturn(staffList);
		
		List<Staff> findByLastName = staffService.findByLastName("TEMBEL");
		assertNotNull(findByLastName);
		assertEquals(findByLastName.size(), 2);
		assertEquals(findByLastName.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByLastName.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByLastName.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByLastName.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByLastName.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findByLastName(anyString());
		
		logger.info("testFindByLastName is successful.");
	}
	
	@Test
	public void testFindByPhone() {
		logger.info("testFindByPhone is started.");
		
		when(mockStaffDAO.findByPhone(anyString())).thenReturn(staffList);
		
		List<Staff> findByPhone = staffService.findByPhone("9879879879");
		assertNotNull(findByPhone);
		assertEquals(findByPhone.size(), 2);
		assertEquals(findByPhone.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByPhone.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByPhone.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByPhone.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByPhone.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findByPhone(anyString());
		
		logger.info("testFindByPhone is successful.");
	}
	
	@Test
	public void testFindByEmail() {
		logger.info("testFindByEmail is started.");
		
		when(mockStaffDAO.findByEmail(anyString())).thenReturn(staffList);
		
		List<Staff> findByEmail = staffService.findByEmail("ahmet@abc.com");
		assertNotNull(findByEmail);
		assertEquals(findByEmail.size(), 2);
		assertEquals(findByEmail.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByEmail.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByEmail.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByEmail.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByEmail.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findByEmail(anyString());
		
		logger.info("testFindByEmail is successful.");
	}
	
	@Test
	public void testFindByRegisteredTime() {
		logger.info("testFindByRegisteredTime is started.");
		
		when(mockStaffDAO.findByRegisteredTime(any(Date.class))).thenReturn(staffList);
		
		List<Staff> findByRegisteredTime = staffService.findByRegisteredTime(staffList.get(0).getRegisteredTime());
		
		assertNotNull(findByRegisteredTime);
		assertEquals(findByRegisteredTime.size(), 2);
		assertEquals(findByRegisteredTime.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByRegisteredTime.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByRegisteredTime.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByRegisteredTime.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByRegisteredTime.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByRegisteredTime(any(Date.class));
		
		logger.info("testFindByRegisteredTime is successful.");
	}
	
	@Test
	public void testFindByDepartment() {
		logger.info("testFindByDepartment is started.");
		
		when(mockStaffDAO.findByDepartment(any(Department.class))).thenReturn(staffList);
		
		List<Staff> findByDepartment = staffService.findByDepartment(staffList.get(0).getDepartment());
		
		assertNotNull(findByDepartment);
		assertEquals(findByDepartment.size(), 2);
		assertEquals(findByDepartment.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByDepartment.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByDepartment.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByDepartment.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByDepartment.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByDepartment(any(Department.class));
		
		logger.info("testFindByDepartment is successful.");
	}
	
	@Test
	public void testFindByDepartmentId() {
		logger.info("testFindByDepartmentId is started.");
		
		when(mockStaffDAO.findByDepartment_Id(any(Integer.class))).thenReturn(staffList);
		
		List<Staff> findByDepartment_Id = staffService.findByDepartmentId(staffList.get(0).getDepartment().getId());
		
		assertNotNull(findByDepartment_Id);
		assertEquals(findByDepartment_Id.size(), 2);
		assertEquals(findByDepartment_Id.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByDepartment_Id.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByDepartment_Id.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByDepartment_Id.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByDepartment_Id.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByDepartment_Id(any(Integer.class));
		
		logger.info("testFindByDepartmentId is successful.");
	}
}
