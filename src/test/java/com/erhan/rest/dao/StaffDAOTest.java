package com.erhan.rest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

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
import com.erhan.rest.model.Staff;

@RunWith(MockitoJUnitRunner.class)
public class StaffDAOTest {
	
	public static final Logger logger = LogManager.getLogger(DepartmentDAOTest.class);
	
	@InjectMocks
	StaffDAO staffDAO;

	@Mock
	Session mockSession;
	
	@Mock
	CriteriaBuilder mockCriteriaBuilder;
	
	@Mock
	CriteriaQuery<Staff> mockCriteriaQuery;
	
	@Mock
	Root<Staff> mockRoot;
	
	@Mock
	Query<Staff> mockQuery;
	
	private Department department;
	
	@Before
	public void setUp() {
		department = new Department("Üretim");
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(new Staff("Mehmet", "ÇALIŞKAN", "1231231231", "mehmet@abc.com", new Date(), department));
		staffList.add(new Staff("Ahmet", "TEMBEL", "9879879879", "ahmet@abc.com", new Date(), department));
		
		when(mockSession.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
		when(mockCriteriaBuilder.createQuery(Staff.class)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.from(Staff.class)).thenReturn(mockRoot);
		when(mockCriteriaQuery.select(mockRoot)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)))).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.where(mockCriteriaBuilder.equal(any(Expression.class), any(Date.class)))).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.where(mockCriteriaBuilder.equal(any(Expression.class), any(Department.class)))).thenReturn(mockCriteriaQuery);
		when(mockSession.createQuery(mockCriteriaQuery)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(staffList);
	}
	
	@Test
	public void testFindAll() {
		logger.info("testFindAll is started.");
		
		List<Staff> allStaffs = staffDAO.findAll();
		checkStaffListWithAssertion(allStaffs);
		
		verifyCommonActions();
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	public void testFindByFirstName() {
		logger.info("testFindByFirstName is started.");
		
		List<Staff> findByFirtsName = staffDAO.findByFirtsName("Mehmet");
		checkStaffListWithAssertion(findByFirtsName);
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)));
		
		logger.info("testFindByFirstName is successful.");
	}

	
	
	@Test
	public void testFindByLastName() {
		logger.info("testFindByLastName is started.");
		
		List<Staff> findByLastName = staffDAO.findByLastName("ÇALIŞKAN");
		checkStaffListWithAssertion(findByLastName);
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)));
		
		logger.info("testFindByLastName is successful.");
	}
	
	@Test
	public void testFindByPhone() {
		logger.info("testFindByPhone is started.");
		
		List<Staff> findByPhone = staffDAO.findByPhone("1231231231");
		checkStaffListWithAssertion(findByPhone);
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)));
		
		logger.info("testFindByPhone is successful.");
	}
	
	@Test
	public void testFindByEmail() {
		logger.info("testFindByEmail is started.");

		List<Staff> findByEmail = staffDAO.findByEmail("mehmet@abc.com");
		checkStaffListWithAssertion(findByEmail);
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(String.class)));
		
		logger.info("testFindByEmail is successful.");
	}
	
	@Test
	public void testFindByRegisteredTime() {
		logger.info("testFindByRegisteredTime is started.");
		
		List<Staff> findByRegisteredTime = staffDAO.findByRegisteredTime(new Date());
		checkStaffListWithAssertion(findByRegisteredTime);
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(Date.class)));
		
		logger.info("testFindByRegisteredTime is successful.");
	}
	
	@Test
	public void testFindByDepartment() {
		logger.info("testFindByDepartment is started.");
		
		List<Staff> findByDepartment = staffDAO.findByDepartment(department);
		checkStaffListWithAssertion(findByDepartment);
		
		verifyCommonActions();
		verify(mockCriteriaQuery, times(1)).where(mockCriteriaBuilder.equal(any(Expression.class), any(Department.class)));
		
		logger.info("testFindByDepartment is successful.");
	}

	private void verifyCommonActions() {
		verify(mockSession, times(1)).getCriteriaBuilder();
		verify(mockCriteriaBuilder, times(1)).createQuery(any());
		verify(mockCriteriaQuery, times(1)).from(Staff.class);
		verify(mockCriteriaQuery, times(1)).select(mockRoot);
		verify(mockSession, times(1)).createQuery(mockCriteriaQuery);
		verify(mockQuery, times(1)).getResultList();
	}
	
	private void checkStaffListWithAssertion(List<Staff> findByFirtsName) {
		assertNotNull(findByFirtsName);
		assertEquals(findByFirtsName.get(0).getFirstName(), "Mehmet");
		assertEquals(findByFirtsName.get(0).getLastName(), "ÇALIŞKAN");
		assertEquals(findByFirtsName.get(0).getPhone(), "1231231231");
		assertEquals(findByFirtsName.get(0).getEmail(), "mehmet@abc.com");
		assertEquals(findByFirtsName.get(0).getDepartment().getDepartmentName(), "Üretim");
	}
}
