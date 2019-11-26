package com.sportsbettings;

import java.util.List;

public interface ISportsBettingService {

	void savePlayer(Player player);

	Player findPlayer();

	List<SportEvent> findAllSportEvents();

	void saveWager(Wager wager);

	List<Wager> findAllWagers();

	void calculateResults();
}
