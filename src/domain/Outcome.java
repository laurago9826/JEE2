package domain;
import java.util.List;

public class Outcome {
	String description;
	Bet bet;
	List<OutcomeOdd> outcomeOdds;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public List<OutcomeOdd> getOutcomeOdds() {
		return outcomeOdds;
	}

	public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
		this.outcomeOdds = outcomeOdds;
	}

}
