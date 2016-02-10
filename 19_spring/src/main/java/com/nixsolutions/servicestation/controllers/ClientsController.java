
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.service.ClientService;
import com.nixsolutions.servicestation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by rybkinrolla on 17.01.2016.
 */

@Controller
public class ClientsController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    protected String getClients(Model model) {
        fillPage(model);
        return "clients";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    protected String crudClients(@RequestParam(value = "user_login") String login,
                                 @RequestParam(value = "user_password") String password,
                                 @RequestParam(value = "first_name") String firstName,
                                 @RequestParam(value = "last_name") String lastName,
                                 @RequestParam(value = "roles") Long roleId,
                                 @RequestParam(value = "user_id", required = false) Long userId,
                                 @RequestParam(value = "client_id", required = false) Long clientId,
                                 @RequestParam(value = "submitButton") String submitButton,
                                 Model model) {

        if (submitButton.equals("edit")) {
            clientService.updateClientUser(roleId,login,password,firstName,lastName,userId,clientId);
            model.addAttribute("msg", "Row with client_id = " + clientId + " was updated");
        }
        if (submitButton.equals("add")) {
            if (clientService.createClientUser(roleId, login, password, firstName, lastName)) {
                model.addAttribute("msg", "New row was created");
            } else {
                model.addAttribute("msg", "This login is already in use");
                fillPage(model);
                return "clients";
            }
        }
        if (submitButton.equals("delete")) {
            if (clientService.deleteClientUser(roleId,userId,clientId)) {
                model.addAttribute("msg", "Row with client_id = " + clientId + " was deleted");
            } else {
                model.addAttribute("msg", "You can't delete manager from here." +
                        " Please enter database and delete it there");
                fillPage(model);
                return "clients";
            }
        }
        fillPage(model);
        return "clients";
    }

    private void fillPage(Model model) {
        model.addAttribute("ucSet", clientService.findClientsUsers());
        model.addAttribute("roleSet", roleService.findAll());
    }
}

