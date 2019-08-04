package com.erhan.rest.exception;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import com.erhan.rest.model.ErrorMessage;

@Service
@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException exception) {
		ErrorMessage errorMessage = new ErrorMessage("400", exception.getMessage());
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
