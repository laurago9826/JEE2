package domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {

	BigDecimal oddValue;
	LocalDateTime validFrom;
	LocalDateTime validUntil;
	Currency currency;
	Outcome outcome;

	public BigDecimal getOddValue() {
		return oddValue;
	}

	public void setOddValue(BigDecimal oddValue) {
		this.oddValue = oddValue;
	}

	public LocalDateTime getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
}
