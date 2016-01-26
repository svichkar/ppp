package com.nixsolutions.service.wsdl.client;

import java.math.BigInteger;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.nixsolutions.service.wsdl.CreateNewWorkerRequest;
import com.nixsolutions.service.wsdl.CreateNewWorkerResponse;
import com.nixsolutions.service.wsdl.UpdateWorkerRequest;
import com.nixsolutions.service.wsdl.UpdateWorkerResponse;
import com.nixsolutions.service.wsdl.UserRoleService;
import com.nixsolutions.service.wsdl.UserService;
import com.nixsolutions.service.wsdl.WorkerService;
import com.nixsolutions.service.wsdl.WorkerSpecializationService;

public class WorkerServiceClient extends WebServiceGatewaySupport{

	public CreateNewWorkerResponse createNewWorkerResponse(String login, String password, String lastName,
			String firstName, String specId) {

		UserRoleService roleService = new UserRoleService();

		UserService userService = new UserService();
		userService.setLogin(login);
		userService.setPassword(password);
		userService.setUserRole(roleService);
		
		WorkerSpecializationService specializationService = new WorkerSpecializationService();
		specializationService.setWorkerSpecializationId(BigInteger.valueOf(Long.decode(specId)));

		WorkerService workerServ = new WorkerService();
		workerServ.setFirstName(firstName);
		workerServ.setLastName(lastName);
		workerServ.setWorkerSpecialization(specializationService);
		workerServ.setUser(userService);

		CreateNewWorkerRequest newWorkerRequest = new CreateNewWorkerRequest();
		newWorkerRequest.setWorker(workerServ);

		CreateNewWorkerResponse customerResponse = (CreateNewWorkerResponse) getWebServiceTemplate()
				.marshalSendAndReceive(newWorkerRequest,
						new SoapActionCallback("http://localhost:8080/wsSoapSTO/soapws/createNewWorkerRequest"));

		return customerResponse;
	}

	public UpdateWorkerResponse updateWorkerResponse(String userId, String login, String password,
			String workerId, String lastName, String firstName,  String specId ) {

		UserService userService = new UserService();
		userService.setUserId(BigInteger.valueOf(Long.decode(userId)));
		userService.setLogin(login);
		userService.setPassword(password);
		
		WorkerSpecializationService specializationService = new WorkerSpecializationService();
		specializationService.setWorkerSpecializationId(BigInteger.valueOf(Long.decode(specId)));

		WorkerService workerServ = new WorkerService();
		workerServ.setWorkerId(BigInteger.valueOf(Long.decode(workerId)));
		workerServ.setFirstName(firstName);
		workerServ.setLastName(lastName);
		workerServ.setWorkerSpecialization(specializationService);
		workerServ.setUser(userService);

		UpdateWorkerRequest newWorkerRequest = new UpdateWorkerRequest();
		newWorkerRequest.setWorker(workerServ);

		UpdateWorkerResponse customerResponse = (UpdateWorkerResponse) getWebServiceTemplate()
				.marshalSendAndReceive(newWorkerRequest,
						new SoapActionCallback("http://localhost:8080/wsSoapSTO/soapws/updateWorkerResponse"));

		return customerResponse;

	}

}
