package domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class SportEvent {
	String title;
	LocalDateTime startDate;
	LocalDateTime endDate;
	List<Bet> bets;
	Result result;

	public SportEvent(SportEventBuilder seb) {
		this.title = seb.title;
		this.startDate = seb.startDate;
		this.endDate = seb.endDate;
		this.result = seb.result;
		this.bets = new ArrayList<Bet>();
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
