package com.erhan.rest.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.erhan.rest.model.Department;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentDAOTest {

	public static final Logger logger = LogManager.getLogger(DepartmentDAOTest.class);
	
	@InjectMocks
	DepartmentDAO departmentDAO;
	
	@Mock
	Session mockSession;
	
	@Mock
	CriteriaBuilder mockCriteriaBuilder;
	
	@Mock
	CriteriaQuery<Department> mockCriteriaQuery;
	
	@Mock
	Root<Department> mockRoot;
	
	@Mock
	Query<Department> mockQuery;
	
	private Department department;
	private List<Department> departmentList;
	
	@Before
	public void setUp() {
		
		department = new Department("Finans");
		departmentList = new ArrayList<Department>();
		departmentList.add(new Department("Üretim"));
		departmentList.add(new Department("Finans"));
		
		when(mockSession.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
		when(mockCriteriaBuilder.createQuery(Department.class)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.from(Department.class)).thenReturn(mockRoot);
		when(mockCriteriaQuery.select(mockRoot)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)))).thenReturn(mockCriteriaQuery);
		
		when(mockSession.createQuery(mockCriteriaQuery)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(departmentList);
		when(mockQuery.uniqueResult()).thenReturn(department);
	}
	
	@Test
	public void testFindAll() {
		logger.info("testFindAll is started.");
		
		List<Department> allDepartments = departmentDAO.findAll();
		assertNotNull(allDepartments);
		assertEquals(allDepartments.get(0).getDepartmentName(), "Üretim");
		
		verifyCommonActions();
		verify(mockQuery, times(1)).getResultList();
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	public void testFindByDepartmentName() {
		logger.info("testFindByDepartmentName is started.");
		
		Department findByDepartmentName = departmentDAO.findByDepartmentName("Finans");
		assertNotNull(findByDepartmentName);
		assertEquals(findByDepartmentName.getDepartmentName(), "Finans");
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)));
		verify(mockQuery, times(1)).uniqueResult();
		
		logger.info("testFindByDepartmentName is successful.");
	}

	private void verifyCommonActions() {
		verify(mockSession, times(1)).getCriteriaBuilder();
		verify(mockCriteriaBuilder, times(1)).createQuery(any());
		verify(mockCriteriaQuery, times(1)).from(Department.class);
		verify(mockCriteriaQuery, times(1)).select(mockRoot);
		verify(mockSession, times(1)).createQuery(mockCriteriaQuery);
	}
}
