package com.sportbettings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sportsbettings.domain.User;

@Controller
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String def() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login", "user", new User());
		return mv;
	}
}
