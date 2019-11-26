package com.sportsbettings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	protected Long id;
	protected String email;
	protected String password;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
