package com.sportsbettings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sportsbettings.App;
import com.sportsbettings.SportsBettingService;
import com.sportsbettings.View;

@Configuration
public class AppConfig {

	@Bean
	App app() {
		return new App(new SportsBettingService(), new View());
	}
}
