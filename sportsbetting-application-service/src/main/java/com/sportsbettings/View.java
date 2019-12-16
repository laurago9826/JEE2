package com.sportsbettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.sportsbettings.domain.Bet;
import com.sportsbettings.domain.Currency;
import com.sportsbettings.domain.Outcome;
import com.sportsbettings.domain.OutcomeOdd;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Player.PlayerBuilder;
import com.sportsbettings.domain.SportEvent;
import com.sportsbettings.domain.Wager;

public class View implements IView {

	private static Logger LOGGER = LoggerFactory.getLogger(View.class);

	@Autowired
	private MessageSource messageSource;

	@Override
	public Player readPlayerData() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(getEnglishStringBL("nameQuestion", null));
		String name = reader.readLine();
		name = (name.isEmpty() ? getEnglishString("defaultPlayerName", null) : name);
		System.out.print(getEnglishStringBL("balanceQuestion", null));
		BigDecimal balance = new BigDecimal(reader.readLine()).max(new BigDecimal(0));
		System.out.print(getEnglishStringBL("currencyQuestion", null));
		Currency currency = Currency.valueOf(reader.readLine().toUpperCase());
		return PlayerBuilder.newInstance().setName(name).setBalance(balance).setCurrency(currency).build();
	}

	@Override
	public void printWelcomeMessage(Player player) {
		System.out.print(getEnglishStringBL("welcomeMessage", player.getName()));
		LOGGER.info("welcome message printed.");
	}

	@Override
	public void printBalance(Player player) {
		System.out.print(getEnglishStringBL("balanceCheck", player.getBalance(), player.getCurrency()));
	}

	@Override
	public void printOutcomeOdds(List<SportEvent> events) {
		StringBuilder s = new StringBuilder();
		int counter = 1;
		for (SportEvent event : events) {
			for (Bet bet : event.getBets()) {
				for (Outcome outcome : bet.getOutcomes()) {
					for (OutcomeOdd odds : outcome.getOutcomeOdds()) {
						s.setLength(0);
						s.append(counter).
								append(": ")
								.append(getEnglishStringBL("betListItem", event.getTitle(),
										formatDate(event.getStartDate()), outcome.getDescription(),
										bet.getDescription(), odds.getOddValue(), formatDate(odds.getValidFrom()),
										formatDate(odds.getValidUntil())));
						System.out.print(s.toString());
						counter++;
					}
				}
			}
		}
	}

	private String formatDate(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@Override
	public OutcomeOdd selectOutcomeOdd(List<SportEvent> events) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(getEnglishStringBLp("betQuestion", null));
		String selection = reader.readLine();
		if (selection.equals("q"))
			return null;
		int numSelected = Integer.parseInt(selection);
		int counter = 1;
		for (SportEvent event : events) {
			for (Bet bet : event.getBets()) {
				for (Outcome outcome : bet.getOutcomes()) {
					for (OutcomeOdd odd : outcome.getOutcomeOdds()) {
						if (counter == numSelected) {
							return odd;
						}
						counter++;
					}
				}
			}
		}
		return null;
	}

	@Override
	public BigDecimal readWagerAmount() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(getEnglishStringBLp("amountQuestion", null));
		return new BigDecimal(reader.readLine()).max(new BigDecimal(0));
	}

	@Override
	public void printWagerSaved(Wager wager) {
		System.out.print(getEnglishStringBL("wagerSaved", wager.getOdd().getOutcome().getBet().getDescription(),
				wager.getOdd().getOutcome().getBet().getSportEvent().getTitle(), wager.getOdd().getOddValue(),
				wager.getAmount()));
	}

	@Override
	public void printNotEnoughBalance(Player player) {
		System.out.print(getEnglishStringBLp("zeroBalance", player.getBalance()));
	}

	@Override
	public void printResults(Player player, List<Wager> wagers) {
		for (Wager w : wagers) {
			System.out.print(getEnglishStringBL("resultListItem", w.getOdd().getOutcome().getBet().getDescription(),
					w.getOdd().getOutcome().getBet().getSportEvent().getTitle(), w.getOdd().getOddValue(),
					w.getAmount(), w.isWin()));
		}
		System.out.print(getEnglishStringBL("newBalance", player.getBalance(), player.getCurrency()));
	}

	private String getEnglishString(String key, Object... params) {
		return messageSource.getMessage(key, params, Locale.ENGLISH);
	}

	private String getEnglishStringBL(String key, Object... params) {
		return messageSource.getMessage(key, params, Locale.ENGLISH) + "\n";
	}

	private String getEnglishStringBLp(String key, Object param) {
		return messageSource.getMessage(key, new Object[] { param }, Locale.ENGLISH) + "\n";
	}
}
