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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "outcome")
public class Outcome {

	@Id
	@GeneratedValue
	private int id;

	private String description;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "bet_id", referencedColumnName = "id")
	private Bet bet;

	@OneToMany(mappedBy = "outcome", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
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
