package service;

import java.math.BigDecimal;
import java.util.List;

import domain.OutcomeOdd;
import domain.Player;
import domain.SportEvent;
import domain.Wager;

public class View implements IView {

	@Override
	public Player readPlayerData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printWelcomeMessage(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printBalance(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printOutcomeOdds(List<SportEvent> events) {
		// TODO Auto-generated method stub

	}

	@Override
	public OutcomeOdd selectOutcomeOdd(List<SportEvent> events) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal readWagerAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printWagerSaved(Wager wager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printNotEnoughBalance(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printResults(Player player, List<Wager> wagers) {
		// TODO Auto-generated method stub

	}

}
