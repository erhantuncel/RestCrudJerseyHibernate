package com.erhan.rest.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erhan.rest.model.Department;
import com.erhan.rest.resource.bean.DepartmentFilterBean;
import com.erhan.rest.service.DepartmentService;


@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class DepartmentResource {

	private static final Logger logger = LogManager.getLogger(DepartmentResource.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private StaffResource staffResource;
	
	@GET
	@Path("/{departmentId}")
	public Response getDepartmentById(@PathParam("departmentId") Integer id) {
		logger.info("getDepartmentById method is invoked.");
		Department department = departmentService.findById(id);
		Response response = Response.status(Status.OK)
				.entity(department)
				.build();
		return response;
	}
	
	@GET
	public Response getAllDepartments(@BeanParam DepartmentFilterBean filterBean) {
		logger.info("getAllDepartments method is invoked.");
		List<Department> departmentList = null;
		if(filterBean.getPage() != null && filterBean.getSize() != null) {
			departmentList = departmentService.findAllPaginated(filterBean.getPage(), filterBean.getSize());
		} else {
			departmentList = departmentService.findAll();
		}
		Response response = Response.status(Status.OK).entity(departmentList).build();
		return response;
	}
	
	@POST
	public Response createDepartment(Department department, @Context UriInfo uriInfo) {
		logger.info("createDepartment method is invoked.");
		departmentService.create(department);
		String departmentId = String.valueOf(department.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(departmentId).build();
		Response response = Response.created(uri).entity(department).build();
		return response;
	}
	
	@PUT
	@Path("/{departmentId}")
	public Response updateDepartment(@PathParam("departmentId") Integer departmentId, Department department) {
		logger.info("updateDepartment method is invoked.");
		department.setId(departmentId);
		departmentService.update(department);
		Response response = Response.status(Status.OK).entity(department).build();
		return response;
	}
	
	@DELETE
	@Path("/{departmentId}")
	public Response deleteDepartment(@PathParam("departmentId") Integer departmentId) {
		logger.info("deleteDepartment method is invoked.");
		departmentService.removeById(departmentId);
		Response response = Response.status(Status.OK).build();
		return response;
	}
	
	@Path("/{departmentId}/staffs")
	public StaffResource getStaffResource() {
		return staffResource;
	}
}
