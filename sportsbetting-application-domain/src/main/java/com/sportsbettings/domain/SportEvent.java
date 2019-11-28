package com.sportsbettings.domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sport_event")
public abstract class SportEvent {

	@Id
	@GeneratedValue
	private int id;

	private String title;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	// @Fetch(value = FetchMode.SUBSELECT)
	private List<Bet> bets = new ArrayList<Bet>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "result_id", referencedColumnName = "id")
	private Result result;


	protected SportEvent(SportEventBuilder seb) {
		this.title = seb.title;
		this.startDate = seb.startDate;
		this.endDate = seb.endDate;
		this.result = seb.result;
	}

	protected SportEvent() {
		// hibernate
	}

	public void addBet(Bet bet) {
		bets.add(bet);
	}

	public List<Bet> getBets() {
		return bets;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	// ---BUILDER---
	public static class SportEventBuilder {
		String title;
		LocalDateTime startDate;
		LocalDateTime endDate;
		Result result;

		private SportEventBuilder() {
		}

		public static SportEventBuilder newInstance() {
			return new SportEventBuilder();
		}

		public SportEventBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		public SportEventBuilder setStartDate(LocalDateTime startDate) {
			this.startDate = startDate;
			return this;
		}

		public SportEventBuilder setEndDate(LocalDateTime endDate) {
			this.endDate = endDate;
			return this;
		}

		public SportEvent buildTennisEvent() {
			return new TennisSportEvent(this);
		}

		public SportEvent buildFootballEvent() {
			return new FootballSportEvent(this);
		}
	}

}
