package com.erhan.rest.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

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
import com.erhan.rest.model.Staff;
import com.erhan.rest.util.HibernateUtil;

public class StaffIT {

	public static final Logger logger = LogManager.getLogger(StaffIT.class);
	
	private static SessionFactory sessionFactory;
	private Session session;
	private Department department;
	private Staff staff;
	
	@BeforeClass
	public static void init() {
		sessionFactory = HibernateUtil.getSessionFactory();		
	}
	
	@Before
	public void setUp() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		department = new Department("Üretim");
		staff = new Staff("Ahmet", "ÇALIŞKAN", "1231231231", "ahmet@abc.com", new Date(), department);
		session.saveOrUpdate(staff);
		logger.info("New Staff (Id = " + staff.getId() + ") is created with Department(Id = " + department.getId() + ")"); 
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
		
		Staff staffMehmet = new Staff("Mehmet", "TEMBEL", "9879879879", "mehmet@abc.com", new Date(), department);
		session.saveOrUpdate(staffMehmet);
		logger.info("staffMehmet (Id = " + staffMehmet.getId() + ") is created with Department(Id = " + department.getId() + ")");
		
		Staff foundStaff = session.find(Staff.class, staffMehmet.getId());
		assertNotNull(foundStaff);
		assertEquals(foundStaff.getId(), staffMehmet.getId());
		assertEquals(foundStaff.getFirstName(), staffMehmet.getFirstName());
		assertEquals(foundStaff.getLastName(), staffMehmet.getLastName());
		assertEquals(foundStaff.getPhone(), staffMehmet.getPhone());
		assertEquals(foundStaff.getEmail(), staffMehmet.getEmail());
		assertEquals(foundStaff.getRegisteredTime(), staffMehmet.getRegisteredTime());
		assertEquals(foundStaff.getDepartment(), staffMehmet.getDepartment());
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testFind() {
		logger.info("testFind is started.");
		
		Staff foundStaff = session.find(Staff.class, staff.getId());
		
		assertNotNull(foundStaff);
		assertEquals(foundStaff.getId(), staff.getId());
		assertEquals(foundStaff.getFirstName(), staff.getFirstName());
		assertEquals(foundStaff.getLastName(), staff.getLastName());
		assertEquals(foundStaff.getPhone(), staff.getPhone());
		assertEquals(foundStaff.getEmail(), staff.getEmail());
		assertEquals(foundStaff.getRegisteredTime(), staff.getRegisteredTime());
		assertEquals(foundStaff.getDepartment(), staff.getDepartment());
		
		logger.info("testFind is successful.");
	}
	
	@Test
	public void testUpdate() {
		logger.info("testUpdate is started.");
		
		Department departmentSatis = new Department("Satış");
		staff.setDepartment(departmentSatis);
		staff.setPhone("5675675677");
		session.update(staff);
		logger.info("After staff update departmentSatis id = " + departmentSatis.getId());
		
		Staff foundStaffAfterUpdate = session.find(Staff.class, staff.getId());
		
		assertNotNull(foundStaffAfterUpdate);
		assertEquals(foundStaffAfterUpdate.getPhone(), staff.getPhone());
		assertEquals(foundStaffAfterUpdate.getDepartment().getDepartmentName(), "Satış");
		
		logger.info("testUpdate is successful.");
	}
	
	@Test
	public void testDelete() {
		logger.info("testDelete is started.");
		
		session.delete(staff);
		
		Staff foundStaffAfterDelete = session.find(Staff.class, staff.getId());		
		assertNull(foundStaffAfterDelete);
		
		logger.info("testDelete is successful.");
	}
}
