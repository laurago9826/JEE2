package com.sportsbettings;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sportsbettings.config.AppConfig;
import com.sportsbettings.config.JPAConfig;
import com.sportsbettings.domain.User;
import com.sportsbettings.domain.Wager;
import com.sportsbettings.domain.Wager.WagerBuilder;

public class AppSpring {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class,
				JPAConfig.class)) {
			App app = appContext.getBean(App.class);
			testJpa(appContext);
			app.play();

		}
	}

	private static void testJpa(ApplicationContext appContext) {
		EntityManagerFactory emf = appContext.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();

		Wager wager = WagerBuilder.newInstance().setAmount(new BigDecimal(35)).build();
		User user = new User("blabla@gmail.com", "blabla");

		em.persist(user);
	}
}
