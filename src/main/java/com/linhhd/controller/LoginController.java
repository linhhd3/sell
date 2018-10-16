package com.linhhd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	@RequestMapping(value="/dang-nhap")
	// o day em fai co required = false. de y lai nhe.
	public String login(@RequestParam(value="error", required= false) String error){
		logger.error(error);
		return "login";
	}
	
	@RequestMapping(value="/dang-xuat")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/dang-nhap";
	}
}
