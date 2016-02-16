package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entities.Part;
import com.nixsolutions.error.CustomException;
import com.nixsolutions.service.PartService;

import org.apache.commons.lang.math.NumberUtils;

@Controller
public class PartController {

	@Autowired
	private PartService partServiceImpl;

	@RequestMapping(value = { "/partAdd", "/partEdit" }, method = RequestMethod.POST)
	public String processPart(@RequestParam(value = "part_id", required = false) String part_id,
			@RequestParam(value = "part_name", required = false) String part_name,
			@RequestParam(value = "vendor", required = false) String vendor,
			@RequestParam(value = "amount", required = false) String amount,
			@RequestParam(value = "action", required = false) String action, Model model) {
		part_id = part_id != null ? part_id : "";
		amount = amount != null ? amount : "";
		int amountParts = NumberUtils.isDigits(amount) ? Integer.parseInt(amount) : 0;
		/// parse car id
		int partId = NumberUtils.isDigits(part_id) ? Integer.parseInt(part_id) : 0;

		if (partId > 0) {
			Part part = partServiceImpl.getPartById(partId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("title", "Edit part");
				model.addAttribute("part", part);
				model.addAttribute("jsForPage", "part");
				return "part";
			} else if (action.equalsIgnoreCase("Delete")) {
				partServiceImpl.deletePart(part);
				model.addAttribute("parts", partServiceImpl.getAllPart());
				model.addAttribute("title", "Parts");
				return "parts";
			} else if (action.equalsIgnoreCase("Save")) {
				if (amountParts == 0)
				{
					throw new CustomException("403", "Amount cannot be null");
				}
				part.setAmount(amountParts);
				part.setPartName(part_name);
				part.setVendor(vendor);
				partServiceImpl.updatePart(part);
				model.addAttribute("parts", partServiceImpl.getAllPart());
				model.addAttribute("title", "Parts");
				return "parts";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("title", "Add part");
				model.addAttribute("jsForPage", "part");
				return "part";
			} else if (action.equalsIgnoreCase("Save")) {
				Part part = new Part(part_name, vendor, amountParts);
				if (amountParts == 0)
				{
					throw new CustomException("403", "Amount cannot be null");
				}
				partServiceImpl.addPart(part);
				model.addAttribute("parts", partServiceImpl.getAllPart());
				model.addAttribute("title", "Parts");
				return "parts";
			}

		}
		model.addAttribute("parts", partServiceImpl.getAllPart());
		model.addAttribute("title", "Parts");
		return "parts";
	}

}
