package domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
	BigDecimal amount;
	LocalDateTime timestampCreated;
	boolean processed;
	boolean win;
	OutcomeOdd odd;
	Currency currency;
	Player player;
}
