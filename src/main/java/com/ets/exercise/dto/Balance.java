package com.ets.exercise.dto;

import java.util.Date;

public class Balance {

	private Date recentTransactionDate;
	
	private Double lastTransactionAmount;
	
	private Double totalAmount;

	public Date getRecentTransactionDate() {
		return recentTransactionDate;
	}

	public void setRecentTransactionDate(Date recentTransactionDate) {
		this.recentTransactionDate = recentTransactionDate;
	}

	public Double getLastTransactionAmount() {
		return lastTransactionAmount;
	}

	public void setLastTransactionAmount(Double lastTransactionAmount) {
		this.lastTransactionAmount = lastTransactionAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
