package service;

import java.util.List;

import domain.Player;
import domain.SportEvent;
import domain.Wager;

public interface ISportsBettingService {

	void SavePlayer(Player player);

	Player findPlayer();

	List<SportEvent> findAllSportEvents();

	void SaveWager(Wager wager);

	List<Wager> findAllWagers();

	void CalculateResults();
}
