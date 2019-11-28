package com.sportsbettings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
	@Id
	@GeneratedValue
	protected int id;

	protected String email;

	protected String password;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	protected User() {
		// hibernate
	}
}
