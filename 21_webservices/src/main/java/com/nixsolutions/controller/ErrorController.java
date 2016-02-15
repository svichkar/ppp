package com.nixsolutions.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nixsolutions.error.CustomException;

@ControllerAdvice
public class ErrorController {

	
	@ExceptionHandler(CustomException.class)
	public String processError(Model model, CustomException ex) {
		model.addAttribute("ex", ex.getErrMsg());
		model.addAttribute("code", ex.getErrCode());
		model.addAttribute("title", "Oooups! Error!");
		return "errorpage";
	}

	@ExceptionHandler(Exception.class)
	public String processError(Model model, Exception ex) {
		model.addAttribute("ex", ex);
		model.addAttribute("title", "Oooups! Error!");
		return "errorpage";
	}

	@ExceptionHandler(Throwable.class)
	public String processError(Model model, Throwable ex) {
		model.addAttribute("ex", ex);
		model.addAttribute("title", "Oooups! Error!");
		return "errorpage";
	}

}
