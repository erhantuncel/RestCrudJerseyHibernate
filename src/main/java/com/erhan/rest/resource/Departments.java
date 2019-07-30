package com.erhan.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.erhan.rest.model.Department;
import com.erhan.rest.service.DepartmentService;

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Departments {

	private static final Logger logger = LogManager.getLogger(Departments.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@GET
	@Path("/{departmentId}")
	public Department getDepartmentById(@PathParam("departmentId") Integer id) {
		logger.info("getDepartmentById method is invoked.");
		return departmentService.findById(id);
	}
	
	@GET
	public List<Department> getAllDepartments() {
		logger.info("getAllDepartments method is invoked.");
		
		List<Department> allDepartments = departmentService.findAll();
		return allDepartments;
	}
	
	@POST
	public void createDepartment(Department department) {
		logger.info("createDepartment method is invoked.");
		departmentService.create(department);
	}
	
	@PUT
	@Path("/{departmentId}")
	public void updateDepartment(@PathParam("departmentId") Integer departmentId, Department department) {
		logger.info("updateDepartment method is invoked.");
		department.setId(departmentId);
		departmentService.update(department);
	}
	
	@DELETE
	@Path("/{departmentId}")
	public void deleteDepartment(@PathParam("departmentId") Integer departmentId) {
		logger.info("deleteDepartment method is invoked.");
		departmentService.removeById(departmentId);
	}
}
