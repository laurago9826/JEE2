package com.sportsbettings;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sportsbettings.Wager.WagerBuilder;

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
		doBettings();
		calculateResults();
		printResults();
	}

	private void createPlayer() {
		try {
			service.savePlayer(view.readPlayerData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doBettings() {
		try	{
			Player curr = service.findPlayer();
			view.printWelcomeMessage(curr);
			view.printBalance(curr);
			boolean exit = false;
			while (!exit) {
				view.printOutcomeOdds(service.findAllSportEvents());
				OutcomeOdd odd = view.selectOutcomeOdd(service.findAllSportEvents());
				exit = odd == null ? true : false;
				if (exit) {
					break;
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void calculateResults() {
		service.calculateResults();
	}

	private void printResults() {
		view.printResults(service.findPlayer(), service.findAllWagers());
	}
}
