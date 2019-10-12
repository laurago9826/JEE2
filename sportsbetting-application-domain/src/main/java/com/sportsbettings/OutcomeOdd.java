package com.sportsbettings;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {

	BigDecimal oddValue;
	LocalDateTime validFrom;
	LocalDateTime validUntil;
	Currency currency;
	Outcome outcome;

	public OutcomeOdd(OutcomeOddBuilder oob) {
		this.currency = oob.currency;
		this.oddValue = oob.oddValue;
		this.outcome = oob.outcome;
		this.validFrom = oob.validFrom;
		this.validUntil = oob.validUntil;

		this.outcome.addOdds(this);
	}

	public BigDecimal getOddValue() {
		return oddValue;
	}

	public LocalDateTime getValidFrom() {
		return validFrom;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	// ---BUILDER---
	public static class OutcomeOddBuilder {
		BigDecimal oddValue;
		LocalDateTime validFrom;
		LocalDateTime validUntil;
		Currency currency;
		Outcome outcome;

		private OutcomeOddBuilder() {
		}

		public static OutcomeOddBuilder newInstance() {
			return new OutcomeOddBuilder();
		}

		public OutcomeOddBuilder setOddValue(BigDecimal oddValue) {
			this.oddValue = oddValue;
			return this;
		}

		public OutcomeOddBuilder setValidFrom(LocalDateTime validFrom) {
			this.validFrom = validFrom;
			return this;
		}

		public OutcomeOddBuilder setValidUntil(LocalDateTime validUntil) {
			this.validUntil = validUntil;
			return this;
		}

		public OutcomeOddBuilder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public OutcomeOddBuilder setOutcome(Outcome outcome) {
			this.outcome = outcome;
			return this;
		}

		public OutcomeOdd build() {
			return new OutcomeOdd(this);
		}
	}
}
