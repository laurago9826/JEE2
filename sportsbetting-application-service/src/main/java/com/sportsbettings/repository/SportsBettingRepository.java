package com.sportsbettings.repository;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class SportsBettingRepository<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;


}
