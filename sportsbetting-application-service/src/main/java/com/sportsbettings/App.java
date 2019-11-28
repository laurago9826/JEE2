package com.sportsbettings;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sportsbettings.domain.OutcomeOdd;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Wager;
import com.sportsbettings.domain.Wager.WagerBuilder;

public class App {

	@Autowired
	ISportsBettingService service;
	@Autowired
	IView view;

	private static Logger LOGGER = LoggerFactory.getLogger(App.class);

	public App() {
	}

	public void play() {
		createPlayer();
		service.createTestData();
		doBettings();
		calculateResults();
		printResults();
	}

	private void createPlayer() {
		try {
			service.savePlayer(view.readPlayerData());
		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	private void doBettings() {
		try	{
			Player curr = service.findPlayer();
			view.printWelcomeMessage(curr);
			view.printBalance(curr);
			boolean exit = false;
			while (!exit) {
				exit = doTheActualBetting(curr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean doTheActualBetting(Player curr) throws IOException {
		view.printOutcomeOdds(service.findAllSportEvents());
		OutcomeOdd odd = view.selectOutcomeOdd(service.findAllSportEvents());
		boolean exit = odd == null || curr.getBalance().intValue() < 1 ? true : false;
		if (exit) {
			return true;
		}
		BigDecimal amount = view.readWagerAmount();
		while (amount.compareTo(curr.getBalance()) > 0) {
			view.printNotEnoughBalance(curr);
			amount = view.readWagerAmount();
		}
		Wager wager = WagerBuilder.newInstance().setAmount(amount).setCurrency(curr.getCurrency()).setOdd(odd)
				.setPlayer(curr).build();
		service.saveWager(wager);
		view.printWagerSaved(wager);
		view.printBalance(curr);
		return exit;
	}

	private void calculateResults() {
		service.calculateResults();
	}

	private void printResults() {
		view.printResults(service.findPlayer(), service.findAllWagers());
	}
}
