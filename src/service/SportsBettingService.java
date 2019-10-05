package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.Bet;
import domain.Bet.BetBuilder;
import domain.BetType;
import domain.Currency;
import domain.Outcome;
import domain.Outcome.OutcomeBuilder;
import domain.OutcomeOdd;
import domain.OutcomeOdd.OutcomeOddBuilder;
import domain.Player;
import domain.SportEvent;
import domain.SportEvent.SportEventBuilder;
import domain.Wager;

public class SportsBettingService implements ISportsBettingService {

	List<Player> players;
	List<SportEvent> events;
	List<Wager> wagers;

	public SportsBettingService() {
		players = new ArrayList<>();
		events = new ArrayList<>();
		wagers = new ArrayList<>();

		createTestData();
	}

	public void SavePlayer(Player player) {
		players.add(player);
	}

	public Player findPlayer() {
		return players.stream().findFirst().get();
	}

	public List<SportEvent> findAllSportEvents() {
		return events;
	}

	public void SaveWager(Wager wager) {
		wagers.add(wager);
		wager.getPlayer().decreaseBalance(wager.getAmount());
	}

	public List<Wager> findAllWagers() {
		return wagers;
	}

	public void CalculateResults() {
		for (Outcome oc : selectWinnerOutcomes()) {
			findAllWagers().stream().forEach(x -> {
				OutcomeOdd odd = calcCorrectOdd(oc, x);
				if (x.getOdd().equals(odd)) {
					x.setWin(true);
					x.getPlayer().increaseBalance(x.getAmount().multiply(odd.getOddValue()));
				}
			});
		}
	}

	private OutcomeOdd calcCorrectOdd(Outcome oc, Wager wager) {
		for (OutcomeOdd odd : oc.getOutcomeOdds()) {
			if (isInBetweenDates(odd.getValidFrom(), odd.getValidUntil(), wager.getTimestampCreated()))
				return odd;
		}
		return null;
	}

	private boolean isInBetweenDates(LocalDateTime from, LocalDateTime until, LocalDateTime check) {
		return from.compareTo(check) <= 0 && check.compareTo(until) <= 0;
	}

	private List<Outcome> selectWinnerOutcomes() {
		List<Outcome> wins = new ArrayList<>();
		Random rand = new Random();
		for(SportEvent event : findAllSportEvents()) {
			for (Bet bet : event.getBets()) {
				int size = bet.getOutcomes().size();
				if (size > 0)
					wins.add(bet.getOutcomes().get(Math.max(rand.nextInt(size) - 1, 0)));
			}
		}
		return wins;
	}

	// ---TEST-DATA---
	public void createTestData() {
			SportEvent event = SportEventBuilder.newInstance().setTitle("bet1")
					.setStartDate(LocalDateTime.of(2019, 3, 26, 15, 15)).setEndDate(LocalDateTime.of(2019, 3, 26, 15, 15))
					.buildTennisEvent();
			events.add(event);
			Bet bet1 = BetBuilder.newInstance().setBetType(BetType.GOALS).setDescription("desc")
					.setSportEvent(event)
					.build();
			Bet bet2 = BetBuilder.newInstance().setBetType(BetType.GOALS).setDescription("desc")
					.setSportEvent(event)
					.build();
			Outcome outcome1 = OutcomeBuilder.newInstance().setDescription("desc2")
					.setBet(bet1)
					.build();
			Outcome outcome2 = OutcomeBuilder.newInstance().setDescription("desc2")
					.setBet(bet1)
					.build();
			Outcome outcome3 = OutcomeBuilder.newInstance().setDescription("desc3")
					.setBet(bet2)
					.build();
			OutcomeOdd odd1 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("3.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 10, 26, 15, 15))
					.setOutcome(outcome1)
					.build();
		OutcomeOdd odd2 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("4.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 10, 26, 15, 15))
					.setOutcome(outcome2)
					.build();
		OutcomeOdd odd3 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("2.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 10, 26, 15, 15))
					.setOutcome(outcome2)
					.build();
		OutcomeOdd odd4 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("5.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 10, 26, 15, 15))
					.setOutcome(outcome3)
					.build();
	}
}
