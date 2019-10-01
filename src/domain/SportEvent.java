package domain;
import java.time.LocalDateTime;
import java.util.List;

public class SportEvent {
	String title;
	LocalDateTime startDate;
	LocalDateTime endDate;
	List<Bet> bets;
	Result result;

	public SportEvent(SportEventBuilder seb) {
		this.title = seb.title;
		this.startDate = seb.startDate;
		this.endDate = seb.endDate;
		this.bets = seb.bets;
		this.result = seb.result;
	}

	// ---BUILDER---
	public static class SportEventBuilder {
		String title;
		LocalDateTime startDate;
		LocalDateTime endDate;
		List<Bet> bets;
		Result result;

		private SportEventBuilder() {
		}

		public SportEventBuilder newInstance() {
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

		public SportEventBuilder setBets(List<Bet> bets) {
			this.bets = bets;
			return this;
		}

		public SportEventBuilder setResult(Result result) {
			this.result = result;
			return this;
		}

		public SportEvent build() {
			return new SportEvent(this);
		}
	}

}
