package com.nixsolutions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("com.nixsolutions")
public class AppConfig {
	
	@Bean(name = "service")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema serviceSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		//wsdl11Definition.set
		wsdl11Definition.setPortTypeName("ServicePort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://localhost:8080/serviceStation");
		wsdl11Definition.setSchema(serviceSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema studentsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/service.xsd"));
	}
}
