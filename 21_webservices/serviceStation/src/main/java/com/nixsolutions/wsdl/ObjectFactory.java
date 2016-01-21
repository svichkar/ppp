//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:18:54 PM EET 
//


package com.nixsolutions.wsdl;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nixsolutions.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nixsolutions.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserService }
     * 
     */
    public UserService createUserService() {
        return new UserService();
    }

    /**
     * Create an instance of {@link WorkerStatusService }
     * 
     */
    public WorkerStatusService createWorkerStatusService() {
        return new WorkerStatusService();
    }

    /**
     * Create an instance of {@link CreateNewWorkerResponse }
     * 
     */
    public CreateNewWorkerResponse createCreateNewWorkerResponse() {
        return new CreateNewWorkerResponse();
    }

    /**
     * Create an instance of {@link CreateNewWorkerRequest }
     * 
     */
    public CreateNewWorkerRequest createCreateNewWorkerRequest() {
        return new CreateNewWorkerRequest();
    }

    /**
     * Create an instance of {@link CustomerService }
     * 
     */
    public CustomerService createCustomerService() {
        return new CustomerService();
    }

    /**
     * Create an instance of {@link UpdateCustomerResponse }
     * 
     */
    public UpdateCustomerResponse createUpdateCustomerResponse() {
        return new UpdateCustomerResponse();
    }

    /**
     * Create an instance of {@link UserRoleService }
     * 
     */
    public UserRoleService createUserRoleService() {
        return new UserRoleService();
    }

    /**
     * Create an instance of {@link WorkerService }
     * 
     */
    public WorkerService createWorkerService() {
        return new WorkerService();
    }

    /**
     * Create an instance of {@link WorkerSpecializationService }
     * 
     */
    public WorkerSpecializationService createWorkerSpecializationService() {
        return new WorkerSpecializationService();
    }

    /**
     * Create an instance of {@link UpdateWorkerResponse }
     * 
     */
    public UpdateWorkerResponse createUpdateWorkerResponse() {
        return new UpdateWorkerResponse();
    }

    /**
     * Create an instance of {@link UpdateWorkerRequest }
     * 
     */
    public UpdateWorkerRequest createUpdateWorkerRequest() {
        return new UpdateWorkerRequest();
    }

    /**
     * Create an instance of {@link CreateNewCustomerResponse }
     * 
     */
    public CreateNewCustomerResponse createCreateNewCustomerResponse() {
        return new CreateNewCustomerResponse();
    }

    /**
     * Create an instance of {@link UpdateCustomerRequest }
     * 
     */
    public UpdateCustomerRequest createUpdateCustomerRequest() {
        return new UpdateCustomerRequest();
    }

    /**
     * Create an instance of {@link CreateNewCustomerRequest }
     * 
     */
    public CreateNewCustomerRequest createCreateNewCustomerRequest() {
        return new CreateNewCustomerRequest();
    }

}
