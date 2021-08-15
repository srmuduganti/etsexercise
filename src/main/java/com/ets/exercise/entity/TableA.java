package com.ets.exercise.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_A")
public class TableA {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;
	
	
	@OneToOne(mappedBy = "tableA")
	private TableB tableB;
	
	public TableA() {
		
	}
	
	public TableA(Long id, String userId, Date transactionDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.transactionDate = transactionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TableB getTableB() {
		return tableB;
	}

	public void setTableB(TableB tableB) {
		this.tableB = tableB;
	}
	
}
