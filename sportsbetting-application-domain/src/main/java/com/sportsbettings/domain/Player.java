package com.sportsbettings.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player extends User {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private Integer accountNumber;

	private BigDecimal balance;

	private LocalDate birth;

	private Currency currency;

	private Player(PlayerBuilder pb) {
		super(pb.email, pb.password);
		this.accountNumber = pb.accountNumber;
		this.balance = pb.balance;
		this.birth = pb.birth;
		this.currency = pb.currency;
		this.name = pb.name;
	}

	private Player() {
		super();
		// for hibernate
	}

	public int getId() {
		return id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void increaseBalance(BigDecimal by) {
		this.balance = balance.add(by);
	}

	public void decreaseBalance(BigDecimal by) {
		this.balance = balance.subtract(by);
	}

	public void setId(int id) {
		this.id = id;
	}

	// ---BUILDER---
	public static class PlayerBuilder {
		String name;
		Integer accountNumber;
		BigDecimal balance;
		LocalDate birth;
		Currency currency;
		String email;
		String password;

		private PlayerBuilder() {
		}

		public static PlayerBuilder newInstance() {
			return new PlayerBuilder();
		}

		public PlayerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public PlayerBuilder setAccountNumber(Integer accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public PlayerBuilder setBalance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public PlayerBuilder setBirth(LocalDate birth) {
			this.birth = birth;
			return this;
		}

		public PlayerBuilder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public PlayerBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public PlayerBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Player build() {
			return new Player(this);
		}
	}
}
