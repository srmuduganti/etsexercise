package com.ets.exercise.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_B")
public class TableB {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "id")
	private TableA tableA;
	
	@Column(name="TRANSACTION_AMOUNT")
	private Double transactionAmount;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TableA getTableA() {
		return tableA;
	}

	public void setTableA(TableA tableA) {
		this.tableA = tableA;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	

}
