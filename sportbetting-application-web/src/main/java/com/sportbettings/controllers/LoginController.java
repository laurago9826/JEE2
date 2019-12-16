package com.sportbettings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sportsbettings.ISportsBettingService;
import com.sportsbettings.domain.User;

@Controller
public class LoginController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ISportsBettingService service;

	@RequestMapping(method = RequestMethod.GET)
	public String def() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login", "user", new User());
		return mv;
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String submit(@RequestParam(value = "email", required = true) String email,
//			@RequestParam(value = "password", required = true) String password, Model model) {
////		String passwordd = passwordEncoder.encode(password);
////		Player p = PlayerBuilder.newInstance().setEmail(email).setPassword(passwordd).build();
////		service.savePlayer(p);
//		return "redirect:/home";
//	}
}
