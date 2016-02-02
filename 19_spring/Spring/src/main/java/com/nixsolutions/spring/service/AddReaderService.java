package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.ClientDAO;
import com.nixsolutions.spring.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Service
public class AddReaderService {
    @Autowired
    ClientDAO clientDAO;
    public Long addClient (String firstName, String lastName, String phone, String email) {
        Client client = new Client();
        client.setClientFirstName(firstName);
        client.setClientLastName(lastName);
        client.setClientPhone(phone);
        client.setClientEmail(email);
        clientDAO.create(client);
        return client.getClientId();
    }
}
