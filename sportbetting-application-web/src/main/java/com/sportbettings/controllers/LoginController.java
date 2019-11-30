package com.sportbettings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportsbettings.ISportsBettingService;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Player.PlayerBuilder;
import com.sportsbettings.domain.User;

@Controller
public class LoginController {

	@Autowired
	private ISportsBettingService service;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, Model model) {
		User user = new User(email, password);
		Player p = PlayerBuilder.newInstance().setEmail(user.getEmail()).setPassword(user.getPassword()).build();
		service.savePlayer(p);
		return "redirect:/home";
	}

}
