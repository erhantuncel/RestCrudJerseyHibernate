package com.erhan.rest.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import com.erhan.rest.model.ErrorMessage;

@Service
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage("404", exception.getMessage());
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
