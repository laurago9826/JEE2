package com.sportsbettings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sportsbettings.domain.Bet;
import com.sportsbettings.domain.Bet.BetBuilder;
import com.sportsbettings.domain.BetType;
import com.sportsbettings.domain.Currency;
import com.sportsbettings.domain.Outcome;
import com.sportsbettings.domain.Outcome.OutcomeBuilder;
import com.sportsbettings.domain.OutcomeOdd;
import com.sportsbettings.domain.OutcomeOdd.OutcomeOddBuilder;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Result;
import com.sportsbettings.domain.SportEvent;
import com.sportsbettings.domain.SportEvent.SportEventBuilder;
import com.sportsbettings.domain.Wager;
import com.sportsbettings.domain.Wager.WagerBuilder;
import com.sportsbettings.repository.PlayerRepository;
import com.sportsbettings.repository.ResultRepository;
import com.sportsbettings.repository.SportEventRepository;
import com.sportsbettings.repository.WagerRepository;

@Service
public class SportsBettingService implements ISportsBettingService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private SportEventRepository sportEventRepository;

	@Autowired
	private WagerRepository wagerRepository;

	private static Player currentPlayer;

	private static Logger LOGGER = LoggerFactory.getLogger(SportsBettingService.class);

	public SportsBettingService() {
	}

	public void savePlayer(Player player) {
		currentPlayer = player;
		playerRepository.save(player);
	}

	public void logoutPlayer() {
		currentPlayer = null;
	}

	public Player findPlayerById(int id) {
		return playerRepository.findById(id).get();
	}

	public Player findPlayer() {
		if (currentPlayer == null) {
			return null;
		}
		return playerRepository.findById(currentPlayer.getId()).get();
	}

	public List<SportEvent> findAllSportEvents() {
		return Lists.newArrayList(sportEventRepository.findAll());
	}

	public void saveWager(Wager wager) {
		if (wager.getPlayer().getBalance().subtract(wager.getAmount()).intValue() > 0) {
			wager.getPlayer().decreaseBalance(wager.getAmount());
			wagerRepository.save(wager);
			playerRepository.save(wager.getPlayer()); // update player balance
		}

	}

	public List<Wager> findAllWagers() {
		return Lists.newArrayList(wagerRepository.findAll());
	}

	public void calculateResults() {
		Result result = new Result();
		List<Wager> wagers = findAllWagers();
		wagers.forEach(x -> x.setProcessed(!eventNotStarted(x)));
		List<Outcome> win_outcomes = selectWinnerOutcomes();
		for (Outcome oc : win_outcomes) {
			wagers.stream().forEach(x -> {
				OutcomeOdd odd = calcCorrectOdd(oc, x);
				if (x.isProcessed() && odd != null && x.getOdd().equals(odd)) {
					x.setWin(true);
					result.addWinnerOutcome(oc);
					x.getPlayer().increaseBalance(x.getAmount().multiply(odd.getOddValue()));
					playerRepository.save(x.getPlayer()); // update player balance
				}
			});
		}
		wagerRepository.saveAll(wagers);
		resultRepository.save(result);
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
		for (SportEvent event : findAllSportEvents()) {
			if (event.getStartDate().isBefore(LocalDateTime.now())) {
				for (Bet bet : event.getBets()) {
					int size = bet.getOutcomes().size();
					if (size > 0)
						wins.add(bet.getOutcomes().get(Math.max(rand.nextInt(size) - 1, 0)));

				}
			}
		}
		return wins;
	}

	public boolean eventNotStarted(Wager w) {
		return w.getOdd().getOutcome().getBet().getSportEvent().getStartDate().isAfter(LocalDateTime.now());
	}

	public void deleteWager(int id) {
		Wager w = wagerRepository.findById(id).get();
		w.getPlayer().increaseBalance(w.getAmount());
		playerRepository.save(w.getPlayer());
		wagerRepository.deleteById(id);
	}

	// ---TEST-DATA---
	public void createTestData() {
		SportEvent event = SportEventBuilder.newInstance().setTitle("event1")
				.setStartDate(LocalDateTime.of(2019, 3, 26, 15, 15)).setEndDate(LocalDateTime.of(2019, 3, 26, 15, 15))
				.buildTennisEvent();
		SportEvent event2 = SportEventBuilder.newInstance().setTitle("event2")
				.setStartDate(LocalDateTime.of(2020, 3, 26, 15, 15)).setEndDate(LocalDateTime.of(2020, 3, 26, 15, 15))
				.buildFootballEvent();
		Bet bet1 = BetBuilder.newInstance().setBetType(BetType.GOALS).setDescription("bet1").setSportEvent(event)
				.build();
		Bet bet2 = BetBuilder.newInstance().setBetType(BetType.GOALS).setDescription("bet2").setSportEvent(event2)
				.build();
		Outcome outcome1 = OutcomeBuilder.newInstance().setDescription("outcome1").setBet(bet1).build();
		Outcome outcome2 = OutcomeBuilder.newInstance().setDescription("outcome2").setBet(bet1).build();
		Outcome outcome3 = OutcomeBuilder.newInstance().setDescription("outcome3").setBet(bet2).build();
		OutcomeOdd odd1 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("3.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 12, 26, 15, 15)).setOutcome(outcome3).build();
		OutcomeOdd odd2 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("4.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 12, 26, 15, 15)).setOutcome(outcome1).build();
		OutcomeOdd odd3 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("2.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 10, 26, 15, 15)).setOutcome(outcome2).build();
		OutcomeOdd odd4 = OutcomeOddBuilder.newInstance().setOddValue(new BigDecimal("5.0")).setCurrency(Currency.EUR)
				.setValidFrom(LocalDateTime.of(2019, 9, 26, 15, 15))
				.setValidUntil(LocalDateTime.of(2019, 12, 26, 15, 15)).setOutcome(outcome3).build();
		sportEventRepository.save(event);
		sportEventRepository.save(event2);
		Wager w = WagerBuilder.newInstance().setAmount(new BigDecimal(100)).setPlayer(currentPlayer)
				.setCurrency(currentPlayer.getCurrency()).setOdd(odd1).build();
		Wager w2 = WagerBuilder.newInstance().setAmount(new BigDecimal(100)).setPlayer(currentPlayer)
				.setCurrency(currentPlayer.getCurrency()).setOdd(odd2).build();
		Wager w3 = WagerBuilder.newInstance().setAmount(new BigDecimal(100)).setPlayer(currentPlayer)
				.setCurrency(currentPlayer.getCurrency()).setOdd(odd3).build();
		Wager w4 = WagerBuilder.newInstance().setAmount(new BigDecimal(100)).setPlayer(currentPlayer)
				.setCurrency(currentPlayer.getCurrency()).setOdd(odd4).build();
		this.saveWager(w);
		this.saveWager(w2);
		this.saveWager(w3);
		this.saveWager(w4);
	}
}
