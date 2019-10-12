package com.sportsbettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sportsbettings.Player.PlayerBuilder;

public class View implements IView {

	@Override
	public Player readPlayerData() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("What is your name?\n");
		String name = reader.readLine();
		System.out.print("What do you wish to be your initial balance?\n");
		BigDecimal balance = new BigDecimal(reader.readLine());
		System.out.print("What is the currency of your account? (EUR/HUF/USD)\n");
		Currency currency = Currency.valueOf(reader.readLine());
		return PlayerBuilder.newInstance().setName(name).setBalance(balance).setCurrency(currency).build();
	}

	@Override
	public void printWelcomeMessage(Player player) {
		System.out.print("Welcome " + player.getName() + "!\n");
	}

	@Override
	public void printBalance(Player player) {
		System.out.print("Your balance is " + player.getBalance() + " " + player.getCurrency() + "\n");
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
						s.append(counter).append(": Sport event: ").append(event.getTitle()).append(" (start ")
								.append(formatDate(event.getStartDate()))
								.append("), Bet: ").append(bet.getDescription())
								.append(", Outcome: ").append(outcome.getDescription()).append(", Actual odd: ")
								.append(odds.getOddValue()).append(" Valid between ")
								.append(formatDate(odds.getValidFrom())).append(" and ")
								.append(formatDate(odds.getValidUntil())).append(".\n");
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
		System.out.print("What do you wish to bet on? (enter the number or select q)\n");
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
		System.out.print("What amount do you wish to bet?\n");
		return new BigDecimal(reader.readLine());
		
	}

	public void printWagerSaved(Wager wager) {
		StringBuilder s = new StringBuilder("Wager: ");
		s.append(wager.getOdd().getOutcome().getBet().getDescription()).append(" of ")
				.append(wager.getOdd().getOutcome().getBet().getSportEvent().getTitle()).append(" [ odd: ")
				.append(wager.getOdd().getOddValue()).append(", amount: ").append(wager.getAmount())
				.append("] saved!\n");
		System.out.print(s.toString());
	}

	@Override
	public void printNotEnoughBalance(Player player) {
		System.out.print("You don't have enough money on your account, your balance is " + player.getBalance() + "!\n");
	}

	@Override
	public void printResults(Player player, List<Wager> wagers) {
		StringBuilder s = new StringBuilder();
		for (Wager w : wagers) {
			s.setLength(0);
			s.append("Wager '").append(w.getOdd().getOutcome().getBet().getDescription()).append("' of ")
					.append(w.getOdd().getOutcome().getBet().getSportEvent().getTitle()).append(" [odd: ")
					.append(w.getOdd().getOddValue()).append(", amount: ").append(w.getAmount()).append("], win: ")
					.append(w.isWin() + "\n");
			System.out.print(s.toString());
		}
		System.out.print("Your new balance is " + player.getBalance() + " " + player.getCurrency()+"\n");

	}

}
