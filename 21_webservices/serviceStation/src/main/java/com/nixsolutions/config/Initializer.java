package com.nixsolutions.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SoapConsumerConfig.class);
		ctx.register(JerseyConfig.class);
		ctx.register(SecurityConfig.class);

		ctx.setServletContext(servletContext);
	}
}