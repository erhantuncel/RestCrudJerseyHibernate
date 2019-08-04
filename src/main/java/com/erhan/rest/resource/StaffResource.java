package com.erhan.rest.resource;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erhan.rest.model.Staff;
import com.erhan.rest.service.StaffService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class StaffResource {

	private static final Logger logger = LogManager.getLogger(StaffResource.class);
	
	@Autowired
	private StaffService staffService;
	
	@GET
	public Response getAllStaffByDepartmentId(@PathParam("departmentId") Integer departmentId,
											 	 @Context UriInfo allUri) throws ParseException {
		MultivaluedMap<String,String> queryParameters = allUri.getQueryParameters();
		logger.info("getAllStaffByDepartmentId method is invoked with " + queryParameters.size() + " parameters");
		if(queryParameters.size() > 0) {
			for(String key : queryParameters.keySet()) {
				logger.info("Query param " + key + " is  = " + queryParameters.getFirst(key));
			}			
		}
		List<Staff> staffList = null;
		if(queryParameters.size() == 0) {
			staffList = staffService.findByDepartmentId(departmentId);
		} else {
			staffList = staffService.findByDepartmentIdAndQueryParameters(departmentId, queryParameters);
		}
		Response response = Response.status(Status.OK).entity(staffList).build();
		return response;
	}
	
	@GET
	@Path("{staffId}")
	public Response getStaffByIdAndDepartmentId(@PathParam("departmentId") Integer departmentId,
											 @PathParam("staffId") Integer staffId) {
		logger.info("getStaffByIdAndDepartmentId method is invoked.");
		Staff staff = staffService.findByIdAndDepartmentId(staffId, departmentId);
		Response response = Response.status(Status.OK).entity(staff).build();
		return response;
	}
	
	@POST
	public Response createStaffForDepartment(@PathParam("departmentId") Integer departmentId,
										 Staff staff, @Context UriInfo uriInfo) {
		logger.info("createStaffForDepartment method is invoked.");
		staffService.createWithDepartmentId(staff, departmentId);
		String staffId = String.valueOf(staff.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(staffId).build();
		Response response = Response.created(uri).entity(staff).build();
		return response;
	}
	
	@PUT
	@Path("/{staffId}")
	public Response updateStaffForDepartment(@PathParam("departmentId") Integer departmentId,
										  @PathParam("staffId") Integer staffId,
										  Staff staff) {
		logger.info("updateStaffForDepartment method is invoked.");
		staffService.updateWithDepartmentId(staffId, departmentId, staff);
		Response response = Response.status(Status.OK).entity(staff).build();
		return response;
	}
	
	@DELETE
	@Path("/{staffId}")
	public Response deleteStaffForDepartment(@PathParam("departmentId") Integer departmentId,
			  							 @PathParam("staffId") Integer staffId) {
		logger.info("deleteStaffForDepartment method is invoked.");
		staffService.removeWithDepartmentId(staffId, departmentId);
		Response response = Response.status(Status.NO_CONTENT).build();
		return response;
	}
}
