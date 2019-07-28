package com.erhan.rest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erhan.rest.dao.DepartmentDAO;
import com.erhan.rest.model.Department;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

	private static final Logger logger = LogManager.getLogger(DepartmentServiceTest.class);
	
	@Mock
	DepartmentDAO mockDepartmentDAO;
	
	@InjectMocks
	DepartmentService departmentService;
	
	private Department department;
	
	@Before
	public void setUp() {
		department = new Department("Üretim");
	}
	
	@Test
	public void testCreate() {
		logger.info("testCreate is started.");
		
		departmentService.create(department);
		
		verify(mockDepartmentDAO, times(1)).openSessionWithTransaction();
		verify(mockDepartmentDAO, times(1)).create(any(Department.class));
		verify(mockDepartmentDAO, times(1)).closeCurrentSessionWithTransaction();
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testUpdate() {
		logger.info("testUpdate is started.");
		
		departmentService.update(department);
		
		verify(mockDepartmentDAO, times(1)).openSessionWithTransaction();
		verify(mockDepartmentDAO, times(1)).update(any(Department.class));
		verify(mockDepartmentDAO, times(1)).closeCurrentSessionWithTransaction();
		
		logger.info("testUpdate is successful.");
	}
	
	@Test
	public void testRemove() {
		logger.info("testRemove is started.");
		
		departmentService.remove(department);
		
		verify(mockDepartmentDAO, times(1)).openSessionWithTransaction();
		verify(mockDepartmentDAO, times(1)).remove(any(Department.class));
		verify(mockDepartmentDAO, times(1)).closeCurrentSessionWithTransaction();
		
		logger.info("testRemove is successful.");
	}
	
	@Test
	public void testFindById() {
		logger.info("testFindById is started.");
		
//		List<Department> departmentList = new ArrayList<Department>();
//		departmentList.add(new Department("Üretim"));
//		departmentList.add(new Department("Finans"));
		
		when(mockDepartmentDAO.findById(2)).thenReturn(department);
		
		Department findById = departmentService.findById(2);
		assertNotNull(findById);
		assertEquals(findById.getDepartmentName(), department.getDepartmentName());
		
		verify(mockDepartmentDAO, times(1)).openSessionWithTransaction();
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		verify(mockDepartmentDAO, times(1)).closeCurrentSessionWithTransaction();
		
		logger.info("testFindById is successful.");
	}
	
	@Test
	public void testFindAll() {
		logger.info("testFindAll is started.");
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(new Department("Üretim"));
		departmentList.add(new Department("Finans"));
		
		when(mockDepartmentDAO.findAll()).thenReturn(departmentList);
		
		List<Department> findAll = departmentService.findAll();
		assertNotNull(findAll);
		assertEquals(findAll.size(), 2);
		assertEquals(findAll.get(0).getDepartmentName(), "Üretim");
		
		verify(mockDepartmentDAO, times(1)).openSessionWithTransaction();
		verify(mockDepartmentDAO, times(1)).findAll();
		verify(mockDepartmentDAO, times(1)).closeCurrentSessionWithTransaction();
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	public void testFindBtDepartmentName() {
		logger.info("testFindBtDepartmentName is started.");
				
		when(mockDepartmentDAO.findByDepartmentName(anyString())).thenReturn(department);
		
		Department findByDepartmentName = departmentService.findByDepartmentName("Üretim");
		assertNotNull(findByDepartmentName);
		assertEquals(findByDepartmentName.getDepartmentName(), "Üretim");
		
		verify(mockDepartmentDAO, times(1)).openSessionWithTransaction();
		verify(mockDepartmentDAO, times(1)).findByDepartmentName(anyString());
		verify(mockDepartmentDAO, times(1)).closeCurrentSessionWithTransaction();
		
		logger.info("testFindBtDepartmentName is successful.");
	}
}
