package com.sportsbettings;

import java.util.ArrayList;
import java.util.List;

public class Bet {
	String description;
	BetType betType;
	SportEvent sportEvent;
	List<Outcome> outcomes;

	public Bet(BetBuilder bb) {
		this.description = bb.description;
		this.betType = bb.betType;
		this.sportEvent = bb.sportEvent;
		this.outcomes = new ArrayList<Outcome>();

		this.sportEvent.addBet(this);
	}
	
	public String getDescription() {
		return description;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public SportEvent getSportEvent() {
		return sportEvent;
	}

	public BetType getBetType() {
		return betType;
	}

	public void addOutcome(Outcome outcome) {
		outcomes.add(outcome);
	}

	// ---BUILDER---
	public static class BetBuilder {
		String description;
		BetType betType;
		SportEvent sportEvent;

		public static BetBuilder newInstance() {
			return new BetBuilder();
		}

		private BetBuilder() {
		}

		public BetBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public BetBuilder setBetType(BetType betType) {
			this.betType = betType;
			return this;
		}

		public BetBuilder setSportEvent(SportEvent sportEvent) {
			this.sportEvent = sportEvent;
			return this;
		}

		public Bet build() {
			return new Bet(this);
		}
	}
}
