package com.erhan.rest.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

@RunWith(SpringRunner.class)
@EnableJpaRepositories("com.erhan.rest.dao")
@ComponentScan("com.erhan.rest.model")
@ContextConfiguration(classes = {SpringConfig.class, DepartmentDAO.class})
public class DepartmentDAOTest {

	public static final Logger logger = LogManager.getLogger(DepartmentDAOTest.class);
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testSave() {
		logger.info("testSave is started.");
		
		Department departmentSatis = new Department("Satış");
		departmentDAO.save(departmentSatis);
		departmentDAO.flush();
		
		assertNotNull(departmentSatis);
		assertEquals(departmentSatis.getId(), 4);
		
		logger.info("testSave is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindAll() {
		logger.info("testFindAll is started.");
		
		List<Department> allDepartments = departmentDAO.findAll();
		
		assertNotNull(allDepartments);
		assertEquals(allDepartments.size(), 3);
		
		logger.info("testFindAll is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindById() {
		logger.info("testFindById is started.");
		
		Department deparmentARGE = departmentDAO.findById(2).orElse(null);
		
		assertNotNull(deparmentARGE);
		assertEquals(deparmentARGE.getId(), 2);
		assertEquals(deparmentARGE.getDepartmentName(), "AR-GE");
		
		logger.info("testFindById is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testDelete() {
		logger.info("testDelete is started.");
		
		Department deparmentARGE = departmentDAO.findById(2).orElse(null);
		assertNotNull(deparmentARGE);
		departmentDAO.delete(deparmentARGE);
		departmentDAO.flush();
		
		List<Department> allDeparment = departmentDAO.findAll();
		assertNotNull(allDeparment);
		assertEquals(allDeparment.size(), 2);
		
		logger.info("testDelete is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:restdbfortest.sql"})
	public void testFindFirstByDepartmentName() {
		logger.info("testFindFirstByDepartmentName is started.");
		
		Department departmentUretim = departmentDAO.findFirstByDepartmentName("Üretim");
		
		assertNotNull(departmentUretim);
		assertEquals(departmentUretim.getId(), 1);
		assertEquals(departmentUretim.getDepartmentName(), "Üretim");
		
		logger.info("testFindFirstByDepartmentName is successful.");
	}
	
}
