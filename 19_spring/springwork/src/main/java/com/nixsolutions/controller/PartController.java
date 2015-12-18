package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.PartService;

@Controller
public class PartController {

	@Autowired
	private PartService partService;
	@Autowired
	private OrderPartService orderPartService;

	@RequestMapping(value = "/admin/addPart.do", method = RequestMethod.GET)
	public String addPart(Model model) {
		model.addAttribute("action", "add");
		return "/WEB-INF/jsp/part.jsp";
	}

	@RequestMapping(value = "/admin/editPart.do", method = RequestMethod.POST)
	public String editPart(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "part_id") long partId, Model model) {
		model.addAttribute("part", partService.getPartById(partId));
		model.addAttribute("action", "edit");
		return "/WEB-INF/jsp/part.jsp";
	}

	@RequestMapping(value = "/admin/deletePart.do", method = RequestMethod.POST)
	public String deletePart(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "part_id") long partId, Model model) {
		Part part = partService.getPartById(partId);
		orderPartService.getOrderPartsByPart(part).forEach(x -> orderPartService.deleteOrderPart(x));
		partService.deletePart(part);
		model.addAttribute("target", "Parts");
		return "/nav.do";
	}

	@RequestMapping(value = "/admin/partPost.do", method = RequestMethod.POST)
	public String processPart(@ModelAttribute(value = "id") String partId,
			@ModelAttribute(value = "part_name") String partName, @ModelAttribute(value = "vendor") String partVendor,
			@ModelAttribute(value = "amount") String amount, Model model) {
		Part part = partService.getPartById(partId.equals("") ? 0 : Long.parseLong(partId));
		Long amountLong = amount.equals("") ? 0 : Long.parseLong(amount);
		if (part == null) {
			part = new Part(partName, partVendor, amountLong);
			partService.addPart(part);
		} else {
			part.setPartName(partName);
			part.setVendor(partVendor);
			part.setAmount(amountLong);
			partService.updatePart(part);
		}
		model.addAttribute("target", "Parts");
		return "/nav.do";
	}
}
