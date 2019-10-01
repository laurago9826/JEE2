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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestampCreated() {
		return timestampCreated;
	}

	public void setTimestampCreated(LocalDateTime timestampCreated) {
		this.timestampCreated = timestampCreated;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public OutcomeOdd getOdd() {
		return odd;
	}

	public void setOdd(OutcomeOdd odd) {
		this.odd = odd;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
