package com.erhan.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.erhan.rest.dao.DepartmentDAO;
import com.erhan.rest.dao.StaffDAO;
import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;


@RunWith(MockitoJUnitRunner.class)
public class StaffServiceTest {
	
	public static final Logger logger = LogManager.getLogger(StaffServiceTest.class);
	
	@Mock
	StaffDAO mockStaffDAO;
	
	@Mock
	DepartmentDAO mockDepartmentDAO;
	
	@Mock
	Page<Staff> mockPageStaff;
	
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
		department.getStaffList().add(staff);
	}

	@Test
	public void testCreate() {
		logger.info("testCreate is started.");
		
		staffService.create(staff);
		
		verify(mockStaffDAO, times(1)).save(any(Staff.class));
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testCreateWithDepartmentId() {
		logger.info("testCreateWithDepartmentId is started.");
		
		when(mockDepartmentDAO.findById(anyInt())).thenReturn(Optional.of(department));
		
		staffService.createWithDepartmentId(staff, 2);
		assertEquals(staff.getDepartment(), department);
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		verify(mockStaffDAO, times(1)).save(any(Staff.class));
		
		logger.info("testCreateWithDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testCreateWithDepartmentIdWithException() {
		logger.info("testCreateWithDepartmentIdWithException is started.");
		when(mockDepartmentDAO.findById(anyInt())).thenReturn(Optional.empty());
		
		staffService.createWithDepartmentId(staff, 2);
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		
		logger.info("testCreateWithDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testUpdate() {
		logger.info("testUpdate is started.");
		
		staffService.update(staff);
		
		verify(mockStaffDAO, times(1)).save(any(Staff.class));
		
		logger.info("testUpdate is successful.");
	}
	
	@Test
	public void testUpdateWithDepartmentId() {
		logger.info("testUpdateWithDepartmentId is started.");
		
		when(mockDepartmentDAO.findById(anyInt())).thenReturn(Optional.of(department));
		when(mockStaffDAO.findById(anyInt())).thenReturn(Optional.of(staff));
		
		Staff staffUpdate = new Staff("Mehmet", "ÇALIŞKAN", "4564564566", "mehmet2@abc.com", new Date(), department);
		staffService.updateWithDepartmentId(1,1, staffUpdate);
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		verify(mockStaffDAO, times(1)).findById(anyInt());
		assertEquals(staff.getPhone(), staffUpdate.getPhone());
		assertEquals(staff.getEmail(), staffUpdate.getEmail());
		
		logger.info("testUpdateWithDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testUpdateWithDepartmentIdWithNotFoundDepartment() {
		logger.info("testUpdateWithDepartmentIdWithNotFoundDepartment is started.");
		
		when(mockDepartmentDAO.findById(anyInt())).thenReturn(Optional.empty());
		when(mockStaffDAO.findById(anyInt())).thenReturn(Optional.of(staff));
		
		Staff staffUpdate = new Staff("Mehmet", "ÇALIŞKAN", "4564564566", "mehmet2@abc.com", new Date(), department);
		staffService.updateWithDepartmentId(1, 1, staffUpdate);
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		
		logger.info("testUpdateWithDepartmentIdWithNotFoundDepartment is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testUpdateWithDepartmentIdWithNotFoundStaff() {
		logger.info("testUpdateWithDepartmentIdWithNotFoundStaff is started.");
		
		when(mockDepartmentDAO.findById(anyInt())).thenReturn(Optional.of(department));
		when(mockStaffDAO.findById(anyInt())).thenReturn(Optional.empty());
		
		Staff staffUpdate = new Staff("Mehmet", "ÇALIŞKAN", "4564564566", "mehmet2@abc.com", new Date(), department);
		staffService.updateWithDepartmentId(1, 1, staffUpdate);
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		verify(mockStaffDAO, times(1)).findById(anyInt());
		
		logger.info("testUpdateWithDepartmentIdWithNotFoundStaff is successful.");
	}
	
	@Test(expected = BadRequestException.class)
	public void testUpdateWithDepartmentIdWithBadRequestException() {
		logger.info("testUpdateWithDepartmentId is started.");
		
		Staff unknownStaff = new Staff("Mehmet", "ÇALIŞKAN", "1231231231", "mehmet2@abc.com", new Date(), department);
		
		when(mockDepartmentDAO.findById(anyInt())).thenReturn(Optional.of(department));
		when(mockStaffDAO.findById(anyInt())).thenReturn(Optional.of(unknownStaff));
		
		Staff staffUpdate = new Staff("Mehmet", "ÇALIŞKAN", "4564564566", "mehmet2@abc.com", new Date(), department);
		staffService.updateWithDepartmentId(1, 1, staffUpdate);
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		verify(mockStaffDAO, times(1)).findById(anyInt());
		
		logger.info("testUpdateWithDepartmentId is successful.");
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
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdWithException() {
		logger.info("testFindByIdWithException is started.");
		
		when(mockStaffDAO.findById(2)).thenReturn(Optional.ofNullable(null));
		
		staffService.findById(2);
		
		logger.info("testFindByIdWithException is successful.");
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
	public void testFindAllPaginated() {
		logger.info("testFindAllPaginated is started.");
		
		when(mockStaffDAO.findAll(any(Pageable.class))).thenReturn(mockPageStaff);
		when(mockPageStaff.getContent()).thenReturn(staffList);
		
		List<Staff> allStaffs = staffService.findAllPaginated(1, 2);
		assertNotNull(allStaffs);
		assertEquals(allStaffs.size(), 2);
		assertEquals(allStaffs.get(0).getFirstName(), staff.getFirstName());
		assertEquals(allStaffs.get(0).getLastName(), staff.getLastName());
		assertEquals(allStaffs.get(0).getPhone(), staff.getPhone());
		assertEquals(allStaffs.get(0).getEmail(), staff.getEmail());
		assertEquals(allStaffs.get(0).getRegisteredTime(), staff.getRegisteredTime());
		
		verify(mockStaffDAO, times(1)).findAll(any(Pageable.class));
		verify(mockPageStaff, times(1)).getContent();
		
		logger.info("testFindAllPaginated is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindAllPaginatedWithException() {
		logger.info("testFindAllPaginatedWithException is started.");
		
		when(mockStaffDAO.findAll(any(Pageable.class))).thenReturn(mockPageStaff);
		when(mockPageStaff.getContent()).thenReturn(new ArrayList<Staff>());
		
		staffService.findAllPaginated(1, 2);
		
		logger.info("testFindAllPaginatedWithException is successful.");
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
	
	@Test(expected = NotFoundException.class)
	public void testFindByDepartmentIdWithException() {
		logger.info("testFindByDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByDepartment_Id(any(Integer.class))).thenReturn(new ArrayList<Staff>());
		
		staffService.findByDepartmentId(staffList.get(0).getDepartment().getId());
		
		logger.info("testFindByDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByDepartmentIdPaginated() {
		logger.info("testFindByDepartmentIdPaginated is started.");
		
		when(mockStaffDAO.findByDepartment_Id(any(Integer.class), any(Pageable.class))).thenReturn(staffList);
		
		List<Staff> findByDepartment_IdPaginated = staffService
				.findByDepartmentIdPaginated(staffList.get(0).getDepartment().getId(), 0, 2);
		
		assertNotNull(findByDepartment_IdPaginated);
		assertEquals(findByDepartment_IdPaginated.size(), 2);
		assertEquals(findByDepartment_IdPaginated.get(1).getFirstName(), staffList.get(1).getFirstName());
		assertEquals(findByDepartment_IdPaginated.get(1).getLastName(), staffList.get(1).getLastName());
		assertEquals(findByDepartment_IdPaginated.get(1).getPhone(), staffList.get(1).getPhone());
		assertEquals(findByDepartment_IdPaginated.get(1).getEmail(), staffList.get(1).getEmail());
		assertEquals(findByDepartment_IdPaginated.get(1).getRegisteredTime(), staffList.get(1).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByDepartment_Id(any(Integer.class), any(Pageable.class));
		
		logger.info("testFindByDepartmentIdPaginated is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByDepartmentIdWithExceptionPaginated() {
		logger.info("testFindByDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByDepartment_Id(any(Integer.class), any(Pageable.class))).thenReturn(new ArrayList<Staff>());
		
		staffService.findByDepartmentIdPaginated(staffList.get(0).getDepartment().getId(), 0, 2);
		
		logger.info("testFindByDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByIdAndDepartmentId() {
		logger.info("testFindByIdAndDepartmentId is started.");
		
		when(mockStaffDAO.findByIdAndDepartment_Id(anyInt(), anyInt())).thenReturn(staffList);
		
		Staff findByIdAndDepartmentId = staffService.findByIdAndDepartmentId(2, 3);
		assertNotNull(findByIdAndDepartmentId);
		assertEquals(findByIdAndDepartmentId.getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByIdAndDepartmentId.getLastName(), staffList.get(0).getLastName());
		assertEquals(findByIdAndDepartmentId.getPhone(), staffList.get(0).getPhone());
		assertEquals(findByIdAndDepartmentId.getEmail(), staffList.get(0).getEmail());
		assertEquals(findByIdAndDepartmentId.getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByIdAndDepartment_Id(anyInt(), anyInt());
		
		logger.info("testFindByIdAndDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdAndDepartmentIdWithException() {
		logger.info("testFindByIdAndDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByIdAndDepartment_Id(anyInt(), anyInt())).thenReturn(new ArrayList<Staff>());
		
		staffService.findByIdAndDepartmentId(2, 3);
		
		logger.info("testFindByIdAndDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByFirstNameAndDepartmentId() {
		logger.info("testFindByFirstNameAndDepartmentId is started.");
		
		when(mockStaffDAO.findByFirstNameAndDepartment_Id(any(String.class), anyInt())).thenReturn(staffList);
		
		List<Staff> findByFirstNameAndDepartmentId = staffService.findByFirstNameAndDepartmentId("Ahmet", 2);
		assertNotNull(findByFirstNameAndDepartmentId);
		assertEquals(findByFirstNameAndDepartmentId.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByFirstNameAndDepartmentId.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByFirstNameAndDepartmentId.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByFirstNameAndDepartmentId.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByFirstNameAndDepartmentId.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByFirstNameAndDepartment_Id(any(String.class), anyInt());
		
		logger.info("testFindByFirstNameAndDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByFirstNameAndDepartmentIdWithException() {
		logger.info("testFindByFirstNameAndDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByFirstNameAndDepartment_Id(any(String.class), anyInt())).thenReturn(new ArrayList<Staff>());
		staffService.findByFirstNameAndDepartmentId("Ahmet", 2);
		
		logger.info("testFindByFirstNameAndDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByFirstNameAndDepartmentIdPaginated() {
		logger.info("testFindByFirstNameAndDepartmentIdPaginated is started.");
		
		when(mockStaffDAO.findByFirstNameAndDepartment_Id(any(String.class), anyInt(), any(Pageable.class))).thenReturn(staffList);
		
		List<Staff> findByFirstNameAndDepartmentIdPaginated = staffService.findByFirstNameAndDepartmentIdPaginated("Ahmet", 2, 0, 2);
		assertNotNull(findByFirstNameAndDepartmentIdPaginated);
		assertEquals(findByFirstNameAndDepartmentIdPaginated.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByFirstNameAndDepartmentIdPaginated.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByFirstNameAndDepartmentIdPaginated.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByFirstNameAndDepartmentIdPaginated.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByFirstNameAndDepartmentIdPaginated.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByFirstNameAndDepartment_Id(any(String.class), anyInt(), any(Pageable.class));
		
		logger.info("testFindByFirstNameAndDepartmentIdPaginated is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByFirstNameAndDepartmentIdPaginatedWithException() {
		logger.info("testFindByFirstNameAndDepartmentIdPaginatedWithException is started.");
		
		when(mockStaffDAO.findByFirstNameAndDepartment_Id(any(String.class), anyInt(), any(Pageable.class)))
						.thenReturn(new ArrayList<Staff>());
		
		staffService.findByFirstNameAndDepartmentIdPaginated("Ahmet", 2, 0, 2);
		
		logger.info("testFindByFirstNameAndDepartmentIdPaginatedWithException is successful.");
	}
	
	@Test
	public void testFindByLastNameAndDepartmentId() {
		logger.info("testFindByLastNameAndDepartmentId is started.");
		
		when(mockStaffDAO.findByLastNameAndDepartment_Id(any(String.class), anyInt())).thenReturn(staffList);
		
		List<Staff> findByLastNameAndDepartmentId = staffService.findByLastNameAndDepartmentId("ÇALIŞKAN", 2);
		assertNotNull(findByLastNameAndDepartmentId);
		assertEquals(findByLastNameAndDepartmentId.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByLastNameAndDepartmentId.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByLastNameAndDepartmentId.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByLastNameAndDepartmentId.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByLastNameAndDepartmentId.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByLastNameAndDepartment_Id(any(String.class), anyInt());
		
		logger.info("testFindByLastNameAndDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByLastNameAndDepartmentIdWithException() {
		logger.info("testFindByLastNameAndDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByLastNameAndDepartment_Id(any(String.class), anyInt())).thenReturn(new ArrayList<Staff>());
		
		staffService.findByLastNameAndDepartmentId("ÇALIŞKAN", 2);
		
		logger.info("testFindByLastNameAndDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByLastNameAndDepartmentIdPaginated() {
		logger.info("testFindByLastNameAndDepartmentIdPaginated is started.");
		
		when(mockStaffDAO.findByLastNameAndDepartment_Id(any(String.class), anyInt(), any(Pageable.class))).thenReturn(staffList);
		
		List<Staff> findByLastNameAndDepartmentIdPaginated = staffService.findByLastNameAndDepartmentIdPaginated("TEMBEL", 2, 0, 2);
		assertNotNull(findByLastNameAndDepartmentIdPaginated);
		assertEquals(findByLastNameAndDepartmentIdPaginated.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByLastNameAndDepartmentIdPaginated.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByLastNameAndDepartmentIdPaginated.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByLastNameAndDepartmentIdPaginated.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByLastNameAndDepartmentIdPaginated.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByLastNameAndDepartment_Id(any(String.class), anyInt(), any(Pageable.class));
		
		logger.info("testFindByLastNameAndDepartmentIdPaginated is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByLastNameAndDepartmentIdPaginatedWithException() {
		logger.info("testFindByFirstNameAndDepartmentIdPaginatedWithException is started.");
		
		when(mockStaffDAO.findByLastNameAndDepartment_Id(any(String.class), anyInt(), any(Pageable.class)))
						.thenReturn(new ArrayList<Staff>());
		
		staffService.findByLastNameAndDepartmentIdPaginated("TEMBEL", 2, 0, 2);
		
		logger.info("testFindByFirstNameAndDepartmentIdPaginatedWithException is successful.");
	}
	
	@Test
	public void testFindByPhoneAndDepartmentId() {
		logger.info("testFindByPhoneAndDepartmentId is started.");
		
		when(mockStaffDAO.findByPhoneAndDepartment_Id(any(String.class), anyInt())).thenReturn(staffList);
		
		List<Staff> findByPhoneAndDepartmentId = staffService.findByPhoneAndDepartmentId("1231231231", 2);
		assertNotNull(findByPhoneAndDepartmentId);
		assertEquals(findByPhoneAndDepartmentId.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByPhoneAndDepartmentId.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByPhoneAndDepartmentId.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByPhoneAndDepartmentId.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByPhoneAndDepartmentId.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByPhoneAndDepartment_Id(any(String.class), anyInt());
		
		logger.info("testFindByPhoneAndDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByPhoneAndDepartmentIdWithException() {
		logger.info("testFindByPhoneAndDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByPhoneAndDepartment_Id(any(String.class), anyInt())).thenReturn(new ArrayList<Staff>());
		
		staffService.findByPhoneAndDepartmentId("1231231231", 2);
		
		logger.info("testFindByPhoneAndDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByEmailAndDepartmentId() {
		logger.info("testFindByEmailAndDepartmentId is started.");
		
		when(mockStaffDAO.findByEmailAndDepartment_Id(any(String.class), anyInt())).thenReturn(staffList);
		
		List<Staff> findByEmailAndDepartmentId = staffService.findByEmailAndDepartmentId("mehmet@abc.com", 2);
		assertNotNull(findByEmailAndDepartmentId);
		assertEquals(findByEmailAndDepartmentId.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByEmailAndDepartmentId.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByEmailAndDepartmentId.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByEmailAndDepartmentId.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByEmailAndDepartmentId.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByEmailAndDepartment_Id(any(String.class), anyInt());
		
		logger.info("testFindByEmailAndDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByEmailAndDepartmentIdWithException() {
		logger.info("testFindByEmailAndDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByEmailAndDepartment_Id(any(String.class), anyInt())).thenReturn(new ArrayList<Staff>());
		
		staffService.findByEmailAndDepartmentId("mehmet@abc.com", 2);
		
		logger.info("testFindByEmailAndDepartmentIdWithException is successful.");
	}
	
	@Test
	public void testFindByRegisteredTimeAndDepartmentId() {
		logger.info("testFindByRegisteredTimeAndDepartmentId is started.");
		
		when(mockStaffDAO.findByRegisteredTimeAndDepartment_Id(any(Date.class), anyInt())).thenReturn(staffList);
		
		List<Staff> findByRegisteredTimeAndDepartmentId = staffService
				.findByRegisteredTimeAndDepartmentId(staffList.get(0).getRegisteredTime(), 2);
		assertNotNull(findByRegisteredTimeAndDepartmentId);
		assertEquals(findByRegisteredTimeAndDepartmentId.get(0).getFirstName(), staffList.get(0).getFirstName());
		assertEquals(findByRegisteredTimeAndDepartmentId.get(0).getPhone(), staffList.get(0).getPhone());
		assertEquals(findByRegisteredTimeAndDepartmentId.get(0).getLastName(), staffList.get(0).getLastName());
		assertEquals(findByRegisteredTimeAndDepartmentId.get(0).getEmail(), staffList.get(0).getEmail());
		assertEquals(findByRegisteredTimeAndDepartmentId.get(0).getRegisteredTime(), staffList.get(0).getRegisteredTime());
		verify(mockStaffDAO, times(1)).findByRegisteredTimeAndDepartment_Id(any(Date.class), anyInt());
		
		logger.info("testFindByRegisteredTimeAndDepartmentId is successful.");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByRegisteredTimeAndDepartmentIdWithException() {
		logger.info("testFindByRegisteredTimeAndDepartmentIdWithException is started.");
		
		when(mockStaffDAO.findByRegisteredTimeAndDepartment_Id(any(Date.class), anyInt())).thenReturn(new ArrayList<Staff>());
		
		staffService.findByRegisteredTimeAndDepartmentId(staffList.get(0).getRegisteredTime(), 2);
		
		logger.info("testFindByRegisteredTimeAndDepartmentIdWithException is successful.");
	}
}
