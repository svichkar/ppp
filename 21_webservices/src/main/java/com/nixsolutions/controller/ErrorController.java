package com.nixsolutions.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.error.CustomException;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(CustomException.class)
	public ModelAndView processError(CustomException ex) {
		ModelAndView model = new ModelAndView("errorpage");
		model.addObject("ex", ex.getErrMsg());
		model.addObject("code", ex.getErrCode());
		model.addObject("title", "Oooups! Error!");
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView processError(Exception ex) {
		ModelAndView model = new ModelAndView("errorpage");
		model.addObject("ex", ex.getMessage());
		model.addObject("title", "Oooups! Error!");
		return model;
	}

	
}
