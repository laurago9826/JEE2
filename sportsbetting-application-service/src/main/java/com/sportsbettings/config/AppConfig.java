package com.sportsbettings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import com.sportsbettings.App;
import com.sportsbettings.ISportsBettingService;
import com.sportsbettings.IView;
import com.sportsbettings.SportsBettingService;
import com.sportsbettings.View;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
	@DependsOn({ "service", "view" })
	App app() {
		return new App();
	}

	@Bean
	@Scope("singleton")
	ISportsBettingService service() {
		return new SportsBettingService();
	}

	@Bean
	@Scope("singleton")
	IView view() {
		return new View();
	}
}
