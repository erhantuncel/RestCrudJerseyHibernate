package com.erhan.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

@ApplicationPath("/rest")
public class AppConfig	extends ResourceConfig {
	
	public AppConfig() {
		packages("com.erhan.rest.resource", "com.erhan.rest.exception");
		register(RequestContextFilter.class);
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
	}
}
