
package com.nixsolutions.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Controller
@RequestMapping("/mngUsrBackbone")
public class BackboneManageUsersController {

	@RequestMapping(method = RequestMethod.GET)
	public String retrieveUsers(Model model) {		
		return "ManageUsersBackbone";
	}

}
