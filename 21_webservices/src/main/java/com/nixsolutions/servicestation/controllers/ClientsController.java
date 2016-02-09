
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.Client;
import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.service.CarService;
import com.nixsolutions.servicestation.service.ClientService;
import com.nixsolutions.servicestation.service.RoleService;
import com.nixsolutions.servicestation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;


/**
 * Created by rybkinrolla on 17.01.2016.
 */

@Controller
public class ClientsController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CarService carService;

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
                                 @RequestParam(value = "user_id",required = false) Long userId,
                                 @RequestParam(value = "client_id",required = false) Long clientId,
                                 @RequestParam(value = "submitButton") String submitButton,
                                 Model model) {
        boolean flag = true;
        User user = new User();
        Client client = new Client();
        Role role = roleService.findById(roleId);
        Set<User> userSet = userService.findAll();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        if ((submitButton.equals("edit")) || (submitButton.equals("add"))) {
            if (submitButton.equals("edit")) {
                user.setUserId(userId);
                userService.update(user);
            }
            if (submitButton.equals("add")) {
                if (!userSet.contains(user)) {
                    userService.create(user);
                    userSet = userService.findAll();
                } else {
                    flag = false;
                    model.addAttribute("msg","This login is already in use");
                    fillPage(model);
                    return "clients";
                }
            }
            if (submitButton.equals("add") && flag) {
                for (User u : userSet) {
                    if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()) && u.getRole().getRoleId().equals(user.getRole().getRoleId())) {
                        client.setUser(u);
                    }
                }
                clientService.create(client);
                model.addAttribute("msg","New row was created");
            }
            if (submitButton.equals("edit")) {
                user.setUserId(userId);
                client.setUser(user);
                client.setClientId(clientId);
                clientService.update(client);
                model.addAttribute("msg","Row with client_id = " + client.getClientId() + " was updated");
            }
        }
        if (submitButton.equals("delete")) {
            if (!role.getRoleName().equals("manager")) {
                Set<Car> carSet = carService.findAll();
                for (Car car:carSet) {
                    if(car.getClient().getClientId().equals(clientId)){
                        carService.delete(car);
                    }
                }
                client.setClientId(clientId);
                clientService.delete(client);
                user.setUserId(userId);
                userService.delete(user);
                model.addAttribute("msg","Row with client_id = " + client.getClientId() + " was deleted");
            } else {
                model.addAttribute("msg","You can't delete manager from here." +
                        " Please enter database and delete it there");
                fillPage(model);
                return "clients";
            }
        }
        fillPage(model);
        return "clients";
    }

    private void fillPage(Model model){
        model.addAttribute("ucSet", clientService.findClientsUsers());
        model.addAttribute("roleSet", roleService.findAll());
    }
}

