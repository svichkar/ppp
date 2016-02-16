package com.nixsolutions.servicestation.endpoint;

import com.nixsolutions.servicestation.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * Created by rybkinrolla on 16.02.2016.
 */
@Endpoint
public class UserEndpoint {
 /*   private static final String NAMESPACE_URI = "http://www.servicestation.nixsolutions.com/wssoap";

    private UserDAO userDAO;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }*/
}