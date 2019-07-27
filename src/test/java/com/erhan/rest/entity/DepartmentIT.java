package com.erhan.rest.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.erhan.rest.model.Department;
import com.erhan.rest.util.HibernateUtil;


public class DepartmentIT {
	
	private static final Logger logger = LogManager.getLogger(DepartmentIT.class);
	
	private static SessionFactory sessionFactory;
	private Session session;
	private Department department;
	
	@BeforeClass
	public static void init() {
		sessionFactory = HibernateUtil.getSessionFactory();		
	}
	
	@Before
	public void setUp() {
		session = sessionFactory.openSession();
		department = new Department("Üretim");
		session.saveOrUpdate(department);
	}
	
	@After
	public void teardown() {
		if(session.getTransaction().isActive()) {			
			session.getTransaction().rollback();
		}
		session.close();
	}
	
	@AfterClass
	public static void cleanUp() {
		sessionFactory.close();		
	}
	
	@Test
	public void testCreate() {
		logger.info("testCreate is started.");
		
		session.beginTransaction();
		Department departmentFinans = new Department("Finans");
		session.saveOrUpdate(departmentFinans);
		
		Department foundDepartment = session.find(Department.class, departmentFinans.getId());
		assertNotNull(foundDepartment);
		assertEquals(foundDepartment.getDepartmentName(), departmentFinans.getDepartmentName());
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testFind() {
		logger.info("testFind is started.");
		
		session.beginTransaction();
		Department foundDepartment = session.find(Department.class, department.getId());
		assertNotNull(foundDepartment);
		assertEquals(foundDepartment.getId(), department.getId());
		assertEquals(foundDepartment.getDepartmentName(), department.getDepartmentName());
		
		logger.info("testFind is successful.");
	}
	
	@Test
	public void testUpdate() {
		logger.info("testUpdate is started.");
		
		session.beginTransaction();
		Department foundDepartment = session.find(Department.class, department.getId());
		foundDepartment.setDepartmentName("AR-GE");
		session.update(foundDepartment);
		
		Department foundDepartmentAfterUpdate = session.find(Department.class, department.getId());
		assertNotNull(foundDepartmentAfterUpdate);
		assertEquals(foundDepartmentAfterUpdate.getDepartmentName(), "AR-GE");
		
		logger.info("testUpdate is successful.");
	}
	
	@Test
	public void testDelete() {
		logger.info("testDelete is started.");
		
		session.beginTransaction();
		session.delete(department);
		
		Department foundDepartmentAfterDelete = session.find(Department.class, department.getId());
		assertNull(foundDepartmentAfterDelete);
		
		logger.info("testDelete is successful.");
	}
	
}
