package domain;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Player extends User {
	public Player(PlayerBuilder pb) {
		super(pb.email, pb.password);
		this.accountNumber = pb.accountNumber;
		this.balance = pb.balance;
		this.birth = pb.birth;
		this.currency = pb.currency;
		this.name = pb.name;
	}

	String name;
	Integer accountNumber;
	BigDecimal balance;
	LocalDate birth;
	Currency currency;

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

		public PlayerBuilder newInstance() {
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
