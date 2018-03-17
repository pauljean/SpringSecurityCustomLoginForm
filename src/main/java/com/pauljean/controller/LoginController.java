package com.pauljean.controller;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	
	
	Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


	@RequestMapping(value = "/home")
	public ModelAndView login(HttpServletRequest request) {
		
		LOGGER.info("in login controller");	

		ModelAndView model;
		
		model = new ModelAndView();
		model.addObject("title", "home");
		
		
		if(request.getUserPrincipal()!=null) {
			model.setViewName("home");
			LOGGER.info("view name home");	
			
		}else {
			
			LOGGER.info("view name login");	
			
			model.setViewName("login");
		}						
		 
		return model;

	}

	
	

}
