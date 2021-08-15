package com.ets.exercise.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_C")
public class TableC {
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="TOTAL_AMOUNT")
	private Double totalAmount;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
