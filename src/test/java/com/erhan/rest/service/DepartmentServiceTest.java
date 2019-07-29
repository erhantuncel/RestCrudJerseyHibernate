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
import java.util.Optional;

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
		
		verify(mockDepartmentDAO, times(1)).save(any(Department.class));
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testUpdate() {
		logger.info("testUpdate is started.");
		
		departmentService.update(department);
		
		verify(mockDepartmentDAO, times(1)).save(any(Department.class));
		
		logger.info("testUpdate is successful.");
	}
	
	@Test
	public void testRemove() {
		logger.info("testRemove is started.");
		
		departmentService.remove(department);
		
		verify(mockDepartmentDAO, times(1)).delete(any(Department.class));
		
		logger.info("testRemove is successful.");
	}
	
	@Test
	public void testFindById() {
		logger.info("testFindById is started.");
		
		when(mockDepartmentDAO.findById(2)).thenReturn(Optional.of(department));
		
		Department findById = departmentService.findById(2);
		assertNotNull(findById);
		assertEquals(findById.getDepartmentName(), department.getDepartmentName());
		
		verify(mockDepartmentDAO, times(1)).findById(anyInt());
		
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
		
		verify(mockDepartmentDAO, times(1)).findAll();
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	public void testFindBtDepartmentName() {
		logger.info("testFindBtDepartmentName is started.");
				
		when(mockDepartmentDAO.findFirstByDepartmentName(anyString())).thenReturn(department);
		
		Department findByDepartmentName = departmentService.findByDepartmentName("Üretim");
		assertNotNull(findByDepartmentName);
		assertEquals(findByDepartmentName.getDepartmentName(), "Üretim");
		
		verify(mockDepartmentDAO, times(1)).findFirstByDepartmentName(anyString());
		
		logger.info("testFindBtDepartmentName is successful.");
	}
}
