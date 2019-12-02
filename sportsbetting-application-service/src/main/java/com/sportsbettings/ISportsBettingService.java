package com.sportsbettings;

import java.util.List;

import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.SportEvent;
import com.sportsbettings.domain.Wager;

public interface ISportsBettingService {

	void savePlayer(Player player);

	Player findPlayer();

	List<SportEvent> findAllSportEvents();

	void saveWager(Wager wager);

	List<Wager> findAllWagers();

	void calculateResults();

	void createTestData();

	Player findPlayerById(int id);

	boolean eventNotStarted(Wager w);

	void deleteWager(int id);

	void logoutPlayer();
}
