package com.sportsbettings;

import java.util.List;

public interface ISportsBettingService {

	void SavePlayer(Player player);

	Player findPlayer();

	List<SportEvent> findAllSportEvents();

	void SaveWager(Wager wager);

	List<Wager> findAllWagers();

	void CalculateResults();
}
