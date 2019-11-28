package com.sportsbettings.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wager")
public class Wager implements Serializable {
	
	private static final long serialVersionUID = 6770402686662447825L;

	@Id
	@GeneratedValue
	private int id;

	private BigDecimal amount;

	private LocalDateTime timestampCreated;

	private boolean processed;

	private boolean win;

	@ManyToOne
	@JoinColumn(name = "odd_id")
	private OutcomeOdd odd;

	private Currency currency;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "player_id", referencedColumnName = "id")
	private Player player;


	private Wager(WagerBuilder wb) {
		this.amount = wb.amount;
		this.currency = wb.currency;
		this.odd = wb.odd;
		this.player = wb.player;
		this.processed = false;
		this.timestampCreated = LocalDateTime.now();
		this.win = false;
	}

	private Wager() {
		// hibernate
	}

	public OutcomeOdd getOdd() {
		return odd;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isWin() {
		return win;
	}

	public LocalDateTime getTimestampCreated() {
		return timestampCreated;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	// ---BUILDER---
	public static class WagerBuilder {
		BigDecimal amount;
		OutcomeOdd odd;
		Currency currency;
		Player player;

		private WagerBuilder() {
		}

		public static WagerBuilder newInstance() {
			return new WagerBuilder();
		}

		public WagerBuilder setAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public WagerBuilder setOdd(OutcomeOdd odd) {
			this.odd = odd;
			return this;
		}

		public WagerBuilder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public WagerBuilder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public Wager build() {
			return new Wager(this);
		}
	}
}
