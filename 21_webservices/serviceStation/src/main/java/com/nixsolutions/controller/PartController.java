package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.PartService;

@Controller
public class PartController {

	@Autowired
	private PartService partService;

	@RequestMapping(value = "/admin/partPage", method = RequestMethod.GET)
	public String loadPartPage(Model model) {
		model.addAttribute("partList", partService.getAllParts());
		return "partPage";
	}

	@RequestMapping(value = "/admin/addNewPart", method = RequestMethod.GET)
	public String addNewPart(Model model) {
		return "newPart";
	}

	@RequestMapping(value = "/admin/createNewPart", method = RequestMethod.POST)
	public String createNewPart(@RequestParam(value = "part_name", required = false) String part_name,
			@RequestParam(value = "vendor", required = false) String vendor,
			@RequestParam(value = "amount", required = false) String amount, Model model) {
		partService.addNewPart(part_name, vendor, Integer.decode(amount));
		model.addAttribute("partList", partService.getAllParts());
		return "partPage";
	}

	@RequestMapping(value = "/admin/updateExistingPart", method = RequestMethod.POST)
	public String updateExistingPart(@RequestParam(value = "part_id", required = false) String part_id, Model model) {
		model.addAttribute("part", partService.getPart(part_id));
		return "editPart";
	}

	@RequestMapping(value = "/admin/updatePart", method = RequestMethod.POST)
	public String updatePart(@RequestParam(value = "part_name", required = false) String partName,
			@RequestParam(value = "vendor", required = false) String vendor,
			@RequestParam(value = "amount", required = false) String amount,
			@RequestParam(value = "part_id", required = false) String part_id, Model model) {

		partService.updateExistingPart(part_id, partName, vendor, amount);
		model.addAttribute("partList", partService.getAllParts());
		return "partPage";

	}

	@RequestMapping(value = "/admin/deletePart", method = RequestMethod.POST)
	public String deletePart(@RequestParam(value = "part_id", required = false) String part_id, Model model) {
		partService.deletePartByID(part_id);
		model.addAttribute("partList", partService.getAllParts());
		return "partPage";
	}
}
