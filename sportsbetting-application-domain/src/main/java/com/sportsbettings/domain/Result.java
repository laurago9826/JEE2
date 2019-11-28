package com.sportsbettings.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "result")
public class Result {

	@Id
	@GeneratedValue
	private int id;

	@OneToMany
	@JoinColumn(name = "win_outcome_id")
	private List<Outcome> winnerOutcomes = new ArrayList<Outcome>();

	public List<Outcome> getWinnerOutcomes() {
		return winnerOutcomes;
	}

	public void setWinnerOutcomes(List<Outcome> winnerOutcomes) {
		this.winnerOutcomes = winnerOutcomes;
	}

	public void addWinnerOutcome(Outcome win) {
		this.winnerOutcomes.add(win);
	}
}
