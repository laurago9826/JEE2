package com.sportsbettings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport_event")
public class FootballSportEvent extends SportEvent {

	@Id
	@GeneratedValue
	private int id;

	public FootballSportEvent(SportEventBuilder builder) {
		super(builder);
	}

	protected FootballSportEvent() {
		super();
	}
}
