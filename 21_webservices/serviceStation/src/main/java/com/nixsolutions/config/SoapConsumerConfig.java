package com.nixsolutions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.nixsolutions.service.wsdl.client.CustomerServiceClient;
import com.nixsolutions.service.wsdl.client.WorkerServiceClient;

@Configuration
public class SoapConsumerConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.nixsolutions.service.wsdl");
		return marshaller;
	}

	@Bean
	public CustomerServiceClient customerServiceClient(Jaxb2Marshaller marshaller) {
		CustomerServiceClient client = new CustomerServiceClient();
		client.setDefaultUri("http://localhost:8080/wsSoapSTO/soapws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

	@Bean
	public WorkerServiceClient workerServiceClient(Jaxb2Marshaller marshaller) {
		WorkerServiceClient client = new WorkerServiceClient();
		client.setDefaultUri("http://localhost:8080/wsSoapSTO/soapws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
