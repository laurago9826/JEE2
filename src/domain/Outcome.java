package domain;
import java.util.List;

public class Outcome {
	String description;
	Bet bet;
	List<OutcomeOdd> outcomeOdds;

	public Outcome(OutcomeBuilder ob) {
		this.description = ob.description;
		this.bet = ob.bet;
		this.outcomeOdds = ob.outcomeOdds;
	}

	// ----BUILDER----
	public static class OutcomeBuilder {
		String description;
		Bet bet;
		List<OutcomeOdd> outcomeOdds;

		public OutcomeBuilder() {
		}

		public OutcomeBuilder newInstance() {
			return new OutcomeBuilder();
		}

		public OutcomeBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public OutcomeBuilder setBet(Bet bet) {
			this.bet = bet;
			return this;
		}

		public OutcomeBuilder setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
			this.outcomeOdds = outcomeOdds;
			return this;
		}

		public Outcome build() {
			return new Outcome(this);
		}
	}
}
