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
import org.springframework.stereotype.Component;

import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;
import com.erhan.rest.service.DepartmentService;
import com.erhan.rest.service.StaffService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class StaffResource {

	private static final Logger logger = LogManager.getLogger(StaffResource.class);
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GET
	public List<Staff> getAllStaffByDepartmentId(@PathParam("departmentId") Integer departmentId) {
		logger.info("getAllStaffByDepartmentId method is invoked.");
		List<Staff> staffListForDepartmentId = staffService.findByDepartmentId(departmentId);
		if(staffListForDepartmentId == null) {
			return null;
		}
		return staffListForDepartmentId;
	}
	
	@GET
	@Path("{staffId}")
	public Staff getStaffByIdAndDepartmentId(@PathParam("departmentId") Integer departmentId,
											 @PathParam("staffId") Integer staffId) {
		logger.info("getStaffByIdAndDepartmentId method is invoked.");
		List<Staff> staffListForDepartmentId = staffService.findByDepartmentId(departmentId);
		Staff staffFoundById = staffService.findById(staffId);
		if( staffListForDepartmentId.size() <= 0) {
			return null;
		} else if(!staffListForDepartmentId.contains(staffFoundById)) {
			return null;
		}
		return staffFoundById;
	}
	
	@POST
	public Staff createStaffForDepartment(@PathParam("departmentId") Integer departmentId,
										 Staff staff) {
		logger.info("createStaffForDepartment method is invoked.");
		Department departmentById = departmentService.findById(departmentId);
		if(departmentById == null) {
			return null;
		}
		staff.setDepartment(departmentById);
		staffService.create(staff);
		return staff;
	}
	
	@PUT
	@Path("/{staffId}")
	public Staff updateStaffForDepartment(@PathParam("departmentId") Integer departmentId,
										  @PathParam("staffId") Integer staffId,
										  Staff staff) {
		logger.info("updateStaffForDepartment method is invoked.");
		List<Staff> staffListForDepartmentId = staffService.findByDepartmentId(departmentId);
		Staff staffToUpdate = staffService.findById(staffId);
		if(staffListForDepartmentId.size() <= 0) {
			return null;
		} else if(!staffListForDepartmentId.contains(staffToUpdate)) {
			return null;
		} else if(staffToUpdate.getId() != staff.getId()) {
			return null;
		}
		staffToUpdate.setFirstName(staff.getFirstName());
		staffToUpdate.setLastName(staff.getLastName());
		staffToUpdate.setPhone(staff.getPhone());
		staffToUpdate.setEmail(staff.getEmail());
		staffToUpdate.setRegisteredTime(staff.getRegisteredTime());
		staffService.update(staffToUpdate);
		return staffToUpdate;
	}
	
	@DELETE
	@Path("/{staffId}")
	public void deleteStaffForDepartment(@PathParam("departmentId") Integer departmentId,
			  							 @PathParam("staffId") Integer staffId) {
		logger.info("deleteStaffForDepartment method is invoked.");
		List<Staff> staffListForDepartmentId = staffService.findByDepartmentId(departmentId);
		Staff staffToDelete = staffService.findById(staffId);
		if((staffListForDepartmentId.size() > 0) && staffListForDepartmentId.contains(staffToDelete)) {
			Department department = departmentService.findById(departmentId);
			department.getStaffList().remove(staffToDelete);
			departmentService.update(department);
			staffService.remove(staffToDelete);
		} 
	}
}
