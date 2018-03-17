package com.pauljean.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


	@RequestMapping(value = {"/","/index"})
	public ModelAndView index(HttpServletRequest request) {
		
		LOGGER.info("in index controller");	

		ModelAndView model;
		
		model = new ModelAndView();
		model.addObject("title", "index");
			
		model.setViewName("index");

		return model;

	}

}
