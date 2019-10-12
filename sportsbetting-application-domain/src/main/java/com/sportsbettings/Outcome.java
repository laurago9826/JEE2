package com.sportsbettings;

import java.util.ArrayList;
import java.util.List;

public class Outcome {
	String description;
	Bet bet;
	List<OutcomeOdd> outcomeOdds;

	public Outcome(OutcomeBuilder ob) {
		this.description = ob.description;
		this.bet = ob.bet;
		this.outcomeOdds = new ArrayList<OutcomeOdd>();

		this.bet.addOutcome(this);
	}

	public void addOdds(OutcomeOdd outcomeOdd) {
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
