package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.service.PartService;

@Controller
public class PartProcessController {

	@Autowired
	private PartService partService;

	@RequestMapping(value = "/partPost.do", method = RequestMethod.POST)
	public String processPart(@ModelAttribute(value = "id") String partId,
			@ModelAttribute(value = "part_name") String partName, @ModelAttribute(value = "vendor") String partVendor,
			@ModelAttribute(value = "amount") String amount, Model model) {
		Part part = partService.getPartById(partId.equals("") ? 0 : Long.parseLong(partId));
		if (part == null) {
			part = new Part(partName, partVendor, Long.parseLong(amount));
			partService.addPart(part);
		} else {
			part.setPartName(partName);
			part.setVendor(partVendor);
			part.setAmount(Long.parseLong(amount));
			partService.updatePart(part);
		}
		model.addAttribute("target", "Parts");
		return "/nav.do";
	}
}
