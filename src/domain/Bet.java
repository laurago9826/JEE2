package domain;
import java.util.List;

public class Bet {
	String description;
	BetType betType;
	SportEvent sportEvent;
	List<Outcome> outcome;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public SportEvent getSportEvent() {
		return sportEvent;
	}

	public void setSportEvent(SportEvent sportEvent) {
		this.sportEvent = sportEvent;
	}

	public List<Outcome> getOutcome() {
		return outcome;
	}

	public void setOutcome(List<Outcome> outcome) {
		this.outcome = outcome;
	}
}
