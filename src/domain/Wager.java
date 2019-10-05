package domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
	BigDecimal amount;
	LocalDateTime timestampCreated;
	boolean processed;
	boolean win;
	OutcomeOdd odd;
	Currency currency;
	Player player;

	public Wager(WagerBuilder wb) {
		this.amount = wb.amount;
		this.currency = wb.currency;
		this.odd = wb.odd;
		this.player = wb.player;
		this.processed = false;
		this.timestampCreated = LocalDateTime.now();
		this.win = false;
	}

	public OutcomeOdd getOdd() {
		return odd;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isWin() {
		return win;
	}

	public LocalDateTime getTimestampCreated() {
		return timestampCreated;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	// ---BUILDER---
	public static class WagerBuilder {
		BigDecimal amount;
		OutcomeOdd odd;
		Currency currency;
		Player player;

		private WagerBuilder() {
		}

		public static WagerBuilder newInstance() {
			return new WagerBuilder();
		}

		public WagerBuilder setAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public WagerBuilder setOdd(OutcomeOdd odd) {
			this.odd = odd;
			return this;
		}

		public WagerBuilder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public WagerBuilder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public Wager build() {
			return new Wager(this);
		}
	}
}
