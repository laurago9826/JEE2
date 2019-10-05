import java.io.IOException;
import java.math.BigDecimal;

import domain.OutcomeOdd;
import domain.Player;
import domain.Wager;
import domain.Wager.WagerBuilder;
import service.ISportsBettingService;
import service.IView;
import service.SportsBettingService;
import service.View;

public class App {

	ISportsBettingService service;
	IView view;

	public App(ISportsBettingService service, IView view) {
		this.service = service;
		this.view = view;
	}

	public void play() {
		createPlayer();
		doBettings();
		calculateResults();
		printResults();
	}

	public static void main(String[] args) {
		App app = new App(new SportsBettingService(), new View());
		app.play();
	}

	private void createPlayer() {
		try {
			service.SavePlayer(view.readPlayerData());
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
				service.SaveWager(wager);
				view.printWagerSaved(wager);
				view.printBalance(curr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void calculateResults() {
		service.CalculateResults();
	}

	private void printResults() {
		view.printResults(service.findPlayer(), service.findAllWagers());
	}
}
