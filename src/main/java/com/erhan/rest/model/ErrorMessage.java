package com.erhan.rest.model;

public class ErrorMessage {
	
	private String code;
	
	private String errorMessage;
	
	public ErrorMessage(String code, String errorMessage) {
		super();
		this.code = code;
		this.errorMessage = errorMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
