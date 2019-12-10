package com.sportsbettings.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bet")
public class Bet {

	@Id
	@GeneratedValue
	private int id;

	private String description;

	private BetType betType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private SportEvent sportEvent;


	@OneToMany(mappedBy = "bet", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Outcome> outcomes = new ArrayList<Outcome>();

	private Bet() {
		// hibernate
	}

	private Bet(BetBuilder bb) {
		this.description = bb.description;
		this.betType = bb.betType;
		this.sportEvent = bb.sportEvent;

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
