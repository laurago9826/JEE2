package com.sportsbettings.domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outcome_odds")
public class OutcomeOdd {

	@Id
	@GeneratedValue
	private Long id;
	private BigDecimal oddValue;
	private LocalDateTime validFrom;
	private LocalDateTime validUntil;
	private Currency currency;
	private Outcome outcome;

	private OutcomeOdd(OutcomeOddBuilder oob) {
		this.currency = oob.currency;
		this.oddValue = oob.oddValue;
		this.outcome = oob.outcome;
		this.validFrom = oob.validFrom;
		this.validUntil = oob.validUntil;

		this.outcome.addOdd(this);
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
