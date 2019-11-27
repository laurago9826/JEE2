package com.sportsbettings.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "result")
public class Result {

	@Id
	@GeneratedValue
	private int id;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Outcome> winnerOutcomes;


	public List<Outcome> getWinnerOutcomes() {
		return winnerOutcomes;
	}

	public void setWinnerOutcomes(List<Outcome> winnerOutcomes) {
		this.winnerOutcomes = winnerOutcomes;
	}
}
