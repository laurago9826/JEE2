package com.sportbettings.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	private MessageSource messageSource;

	@Autowired
	private ISportsBettingService service;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login", "user", new User());
		localizeLoginPage(mv);
		return mv;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, Model model) {
		User user = new User(email, password);
		Player p = PlayerBuilder.newInstance().setEmail(user.getEmail()).setPassword(user.getPassword()).build();
		service.savePlayer(p);
		return "redirect:/home";
	}

	private ModelAndView localizeLoginPage(ModelAndView mv) {
		mv.addObject("welcome", getEnglishStringp("welcome", null));
		mv.addObject("motto", getEnglishStringp("motto", null));
		mv.addObject("login", getEnglishStringp("login", null));
		mv.addObject("register", getEnglishStringp("register", null));
		mv.addObject("orr", getEnglishStringp("or", null));
		mv.addObject("toStart", getEnglishStringp("toStart", null));
		mv.addObject("email", getEnglishStringp("email", null));
		mv.addObject("password", getEnglishStringp("password", null));
		return mv;
	}

	private String getEnglishStringp(String key, Object param) {
		return messageSource.getMessage(key, new Object[] { param }, Locale.ENGLISH);
	}
}
