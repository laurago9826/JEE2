package domain;
import java.util.List;

final class Bet {
	String description;
	BetType betType;
	SportEvent sportEvent;
	List<Outcome> outcome;
	
	public Bet(BetBuilder bb) {
		this.description = bb.description;
		this.betType = bb.betType;
		this.sportEvent = bb.sportEvent;
		this.outcome = bb.outcome;
	}
	

	public static class BetBuilder {
		String description;
		BetType betType;
		SportEvent sportEvent;
		List<Outcome> outcome;

		public static BetBuilder newInstance() {
			return new BetBuilder();
		}

		private BetBuilder() {
		}

		public BetBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public BetBuilder setBetType(BetType betType) {
			this.betType = betType;
			return this;
		}

		public BetBuilder setSportEvent(SportEvent sportEvent) {
			this.sportEvent = sportEvent;
			return this;
		}

		public BetBuilder setOutcome(List<Outcome> outcome) {
			this.outcome = outcome;
			return this;
		}

		public Bet build() {
			return new Bet(this);
		}
	}
}
