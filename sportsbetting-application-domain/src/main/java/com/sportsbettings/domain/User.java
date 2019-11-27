package com.sportsbettings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
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
}
