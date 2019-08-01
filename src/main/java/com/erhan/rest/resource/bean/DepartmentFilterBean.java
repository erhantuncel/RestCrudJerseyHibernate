package com.erhan.rest.resource.bean;

import javax.ws.rs.QueryParam;

public class DepartmentFilterBean {
	

	private @QueryParam("page") Integer page;
	
	private @QueryParam("size") Integer size;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	
}
