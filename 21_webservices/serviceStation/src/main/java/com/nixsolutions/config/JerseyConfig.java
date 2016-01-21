package com.nixsolutions.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.nixsolutions.service.rest.CarServiceJersey;


@Configuration
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.nixsolutions.service.rest");
		register(MoxyJsonFeature.class, CarServiceJersey.class,JsonMoxyConfigurationContextResolver.class);
	}

}
