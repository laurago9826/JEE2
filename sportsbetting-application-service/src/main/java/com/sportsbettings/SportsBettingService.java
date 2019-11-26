package com.sportsbettings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sportsbettings.Bet.BetBuilder;
import com.sportsbettings.Outcome.OutcomeBuilder;
import com.sportsbettings.OutcomeOdd.OutcomeOddBuilder;
import com.sportsbettings.SportEvent.SportEventBuilder;

public class SportsBettingService implements ISportsBettingService {

	List<Player> players = new ArrayList<Player>();
	List<SportEvent> events = new ArrayList<SportEvent>();
	List<Wager> wagers = new ArrayList<Wager>();

	private static Logger LOGGER = LoggerFactory.getLogger(SportsBettingService.class);

	public SportsBettingService() {

		createTestData();
	}

	public void savePlayer(Player player) {
		players.add(player);
		LOGGER.info("Player " + player.getName() + " saved.");
	}

	public Player findPlayer() {
		return players.stream().findFirst().get();
	}

	public List<SportEvent> findAllSportEvents() {
		return events;
	}

	public void saveWager(Wager wager) {
		wagers.add(wager);
		wager.getPlayer().decreaseBalance(wager.getAmount());
	}

	public List<Wager> findAllWagers() {
		return wagers;
	}

	public void calculateResults() {
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
