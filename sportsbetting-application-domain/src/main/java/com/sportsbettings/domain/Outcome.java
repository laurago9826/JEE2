package com.sportsbettings.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outcomes")
public class Outcome {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Bet bet;
	private List<OutcomeOdd> outcomeOdds = new ArrayList<OutcomeOdd>();

	private Outcome(OutcomeBuilder ob) {
		this.description = ob.description;
		this.bet = ob.bet;

		this.bet.addOutcome(this);
	}

	public void addOdd(OutcomeOdd outcomeOdd) {
		this.outcomeOdds.add(outcomeOdd);
	}

	public List<OutcomeOdd> getOutcomeOdds() {
		return outcomeOdds;
	}

	public String getDescription() {
		return description;
	}

	public Bet getBet() {
		return bet;
	}

	// ----BUILDER----
	public static class OutcomeBuilder {
		String description;
		Bet bet;

		public OutcomeBuilder() {
		}

		public static OutcomeBuilder newInstance() {
			return new OutcomeBuilder();
		}

		public OutcomeBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public OutcomeBuilder setBet(Bet bet) {
			this.bet = bet;
			return this;
		}

		public Outcome build() {
			return new Outcome(this);
		}
	}
}
