package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kozlovskij on 2/3/2016.
 */
@Controller
public class AddUserController {
    @Autowired
    private AddUserService addUserService;

    @RequestMapping(value = "/userManagement")
    protected String addUser(
            @RequestParam(value = "edit", required = false) String edit,
            @RequestParam(value = "add", required = false) String add,
            @RequestParam(value = "delete", required = false) String delete,
            @RequestParam(value = "userLogin", required = false) String userLogin,
            @RequestParam(value = "userPassword", required = false) String userPassword,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "userRole", required = false) Long roleId,
            Model model) {
        if (edit != null) {
            addUserService.edit(userId, userLogin, userPassword, roleId);
            model.addAttribute("msg", "User with id " + userId + " updated");
        }
        if (add != null) {
            model.addAttribute("msg", "User with id " + addUserService.add(userLogin, userPassword, roleId) + " added");
        }
        if (delete != null) {
            addUserService.delete(userId);
            model.addAttribute("msg", "User with id " + userId + " deleted");
        }
        model.addAttribute("users", addUserService.users());
        model.addAttribute("roles", addUserService.roles());
        return "addUser";
    }
}
