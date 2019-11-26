package com.sportsbettings.domain;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class Result {

	@Id
	@GeneratedValue
	private Long id;
	private List<Outcome> winnerOutcomes;

	public List<Outcome> getWinnerOutcomes() {
		return winnerOutcomes;
	}

	public void setWinnerOutcomes(List<Outcome> winnerOutcomes) {
		this.winnerOutcomes = winnerOutcomes;
	}
}
