package com.sportsbettings;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface IView {

	Player readPlayerData() throws IOException;

	void printWelcomeMessage(Player player);

	void printBalance(Player player);

	void printOutcomeOdds(List<SportEvent> events);

	OutcomeOdd selectOutcomeOdd(List<SportEvent> events) throws IOException;

	BigDecimal readWagerAmount() throws IOException;

	void printWagerSaved(Wager wager);

	void printNotEnoughBalance(Player player);

	void printResults(Player player, List<Wager> wagers);
}
