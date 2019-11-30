package com.sportbettings.model;

public class RowData {

	private int wagerId;
	private boolean removeIsVisible;
	private String classNameOnButton;
	private int index;
	private String eventTitle;
	private String eventType;
	private String betType;
	private String outcome;
	private String outcomeOdd;
	private String wagerAmount;
	private String isWin;
	private String processed;


	public boolean isRemoveIsVisible() {
		return removeIsVisible;
	}

	public int getIndex() {
		return index;
	}

	public String getEventType() {
		return eventType;
	}

	public String getBetType() {
		return betType;
	}

	public String getOutcome() {
		return outcome;
	}

	public String getOutcomeOdd() {
		return outcomeOdd;
	}

	public String getWagerAmount() {
		return wagerAmount;
	}

	public String getWin() {
		return isWin;
	}

	public String getProcessed() {
		return processed;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public void setOutcomeOdd(String outcomeOdd) {
		this.outcomeOdd = outcomeOdd;
	}

	public void setWagerAmount(String wagerAmount) {
		this.wagerAmount = wagerAmount;
	}

	public void setWin(String isWin) {
		this.isWin = isWin;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public int getWagerId() {
		return wagerId;
	}

	public void setWagerId(int wagerId) {
		this.wagerId = wagerId;
	}

	public void setRemoveIsVisible(boolean removeIsVisible) {
		this.removeIsVisible = removeIsVisible;
	}

	public String getClassNameOnButton() {
		return classNameOnButton;
	}

	public void setClassNameOnButton(String classNameOnButton) {
		this.classNameOnButton = classNameOnButton;
	}

}

