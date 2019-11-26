package com.sportsbettings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport_events")
public class FootballSportEvent extends SportEvent {

	@Id
	@GeneratedValue
	private Long id;

	public FootballSportEvent(SportEventBuilder builder) {
		super(builder);
	}
}
