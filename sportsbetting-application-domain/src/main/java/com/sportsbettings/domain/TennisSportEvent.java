package com.sportsbettings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport_event")
public class TennisSportEvent extends SportEvent {

	@Id
	@GeneratedValue
	private int id;

	private TennisSportEvent() {
		super();
		// hibernate
	}

	protected TennisSportEvent(SportEventBuilder builder) {
		super(builder);
	}
}
