package com.erhan.rest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.erhan.rest.config.SpringConfig;
import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;

@RunWith(SpringRunner.class)
@EnableJpaRepositories("com.erhan.rest.dao")
@ComponentScan("com.erhan.rest.model")
@ContextConfiguration(classes = {SpringConfig.class, StaffDAO.class})
public class StaffDAOTest {
	
	public static final Logger logger = LogManager.getLogger(DepartmentDAOTest.class);
	
	@Autowired
	private StaffDAO staffDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;

	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testSave() {
		logger.info("testSave is started.");
		
		Department departmentSatis = new Department("Satış");
		Staff staff = new Staff("Mehmet", "ÇALIŞKAN", "1231231231", "mehmet@abc.com", new Date(), departmentSatis);
		staffDAO.save(staff);
		staffDAO.flush();
		
		assertNotNull(staff.getId());
		assertEquals(staff.getId(), 4);
		
		logger.info("testSave is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindAll() {
		logger.info("testFindAll is started.");
		
		List<Staff> allStaff = staffDAO.findAll();

		assertNotNull(allStaff);
		assertEquals(allStaff.size(), 3);
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindById() {
		logger.info("testFindById is started.");
		
		Staff staff = staffDAO.findById(2).orElse(null);

		assertNotNull(staff);
		assertNotNull(staff);
		assertEquals(staff.getId(), 2);
		assertEquals(staff.getFirstName(), "Arzu");
		assertEquals(staff.getLastName(), "BULGUR");
		
		
		logger.info("testFindById is successful.");
	}	
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testDelete() {
		logger.info("testDelete is started.");
		
		Staff staffToDelete = staffDAO.findById(1).orElse(null);
		assertNotNull(staffToDelete);
		staffDAO.delete(staffToDelete);
		staffDAO.flush();
		
		List<Staff> allStaff = staffDAO.findAll();
		assertNotNull(allStaff);
		assertEquals(allStaff.size(), 2);
		
		logger.info("testDelete is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByFirstName() {
		logger.info("testFindByFirstName is started.");
		
		List<Staff> findByFirstName = staffDAO.findByFirstName("Arzu");
		assertNotNull(findByFirstName);
		assertEquals(findByFirstName.size(), 1);
		assertEquals(findByFirstName.get(0).getFirstName(), "Arzu");
		
		logger.info("testFindByFirstName is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByLastName() {
		logger.info("testFindByLastName is started.");
		
		List<Staff> findByLastName = staffDAO.findByLastName("BULGUR");
		assertNotNull(findByLastName);
		assertEquals(findByLastName.size(), 1);
		assertEquals(findByLastName.get(0).getLastName(), "BULGUR");
		
		logger.info("testFindByLastName is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByPhone() {
		logger.info("testFindByPhone is started.");
		
		List<Staff> findByPhone = staffDAO.findByPhone("1283663610");
		assertNotNull(findByPhone);
		assertEquals(findByPhone.size(), 1);
		assertEquals(findByPhone.get(0).getPhone(), "1283663610");
		
		logger.info("testFindByPhone is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByEmail() {
		logger.info("testFindByEmail is started.");
		
		List<Staff> findByEmail = staffDAO.findByEmail("arzu@abc.com");
		assertNotNull(findByEmail);
		assertEquals(findByEmail.size(), 1);
		assertEquals(findByEmail.get(0).getEmail(), "arzu@abc.com");
		
		logger.info("testFindByEmail is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByRegisteredTime() throws ParseException {
		logger.info("testFindByRegisteredTime is started.");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateToCheck = df.parse("2019-06-20 18:35:52");
		List<Staff> findByRegisteredTime = staffDAO.findByRegisteredTime(dateToCheck);
		assertNotNull(findByRegisteredTime);
		assertEquals(findByRegisteredTime.size(), 1);
		assertEquals(findByRegisteredTime.get(0).getRegisteredTime().getTime(), dateToCheck.getTime());
		
		logger.info("testFindByRegisteredTime is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByDepartment() throws ParseException {
		logger.info("testFindByDepartment is started.");

		Department departmentId2 = departmentDAO.findById(2).orElse(null);
		List<Staff> findByDepartment = staffDAO.findByDepartment(departmentId2);
		assertNotNull(findByDepartment);
		assertEquals(findByDepartment.size(), 2);
		assertEquals(findByDepartment.get(0).getDepartment().getDepartmentName(), departmentId2.getDepartmentName());
		
		logger.info("testFindByDepartment is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindByDepartmentId() throws ParseException {
		logger.info("testFindByDepartmentId is started.");

		List<Staff> findByDepartmentId = staffDAO.findByDepartmentId(Integer.valueOf("2"));
		assertNotNull(findByDepartmentId);
		assertEquals(findByDepartmentId.size(), 2);
		assertEquals(findByDepartmentId.get(0).getDepartment().getId(), 2);
		assertEquals(findByDepartmentId.get(0).getFirstName(), "Arzu");
		
		logger.info("testFindByDepartmentId is successful.");
	}
}
