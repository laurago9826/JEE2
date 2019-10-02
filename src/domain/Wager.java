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
		this.processed = wb.processed;
		this.timestampCreated = wb.timestampCreated;
		this.win = wb.win;
	}

	public static class WagerBuilder {
		BigDecimal amount;
		LocalDateTime timestampCreated;
		boolean processed;
		boolean win;
		OutcomeOdd odd;
		Currency currency;
		Player player;

		private WagerBuilder() {
		}

		public WagerBuilder newInstance() {
			return new WagerBuilder();
		}

		public WagerBuilder setAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public WagerBuilder setTimestampCreated(LocalDateTime timestampCreated) {
			this.timestampCreated = timestampCreated;
			return this;
		}

		public WagerBuilder setProcessed(boolean processed) {
			this.processed = processed;
			return this;
		}

		public WagerBuilder setWin(boolean win) {
			this.win = win;
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
