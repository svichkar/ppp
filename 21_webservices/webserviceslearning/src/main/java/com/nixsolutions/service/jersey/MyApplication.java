package com.nixsolutions.service.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
	
	public MyApplication() {
		super();
		register(CarServiceJersey.class);
	}
}
