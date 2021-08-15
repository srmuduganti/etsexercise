package com.ets.exercise.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.exercise.dto.Balance;
import com.ets.exercise.dto.TransactionData;
import com.ets.exercise.entity.TableA;
import com.ets.exercise.entity.TableB;
import com.ets.exercise.entity.TableC;
import com.ets.exercise.repository.TableARepository;
import com.ets.exercise.repository.TableBRepository;
import com.ets.exercise.repository.TableCRepository;

@Service
public class TransactionalService {

	@Autowired
	TableARepository tableARepository;

	@Autowired
	TableBRepository tableBRepository;

	@Autowired
	TableCRepository tableCRepository;

	public Balance makeTransaction(TransactionData transactionData) {
		TableA tableA = makeTransactionInTableA(transactionData);
		TableB tableB = makeTransactionInTableBAndC(transactionData, tableA);
		Balance bal = new Balance();
		bal.setLastTransactionAmount(tableB.getTransactionAmount());
		bal.setRecentTransactionDate(tableA.getTransactionDate());
		return bal;
	}
	
	public Balance fetchBalanceByUserId(String userId) {
		Balance balance = new Balance();
		Optional<TableC> tableC = tableCRepository.findById(userId);
		if(tableC.isPresent()) {
			TableA tableA = tableARepository.findTop1ByUserIdOrderByTransactionDateDesc(userId);
			balance.setLastTransactionAmount(tableA.getTableB().getTransactionAmount());
			balance.setRecentTransactionDate(tableA.getTransactionDate());
			balance.setTotalAmount(tableC.get().getTotalAmount());
			return balance;
		}
		return null;
	}

	@Transactional
	private TableA makeTransactionInTableA(TransactionData transactionData) {
		TableA tableA = new TableA();
		tableA.setUserId(transactionData.getUserId());
		tableA.setTransactionDate(new Date());
		tableA = tableARepository.save(tableA);
		return tableA;
	}
	
	@Transactional
	private TableB makeTransactionInTableBAndC(TransactionData transactionData, TableA tableA) {
		TableB tableB = new TableB();
		tableB.setTransactionAmount(transactionData.getTransactionAmount());
		tableB.setTableA(tableA);
		tableBRepository.save(tableB);
		
		TableC tabC = new TableC();
		 Optional<TableC> tableC = tableCRepository.findById(transactionData.getUserId());
		 if(tableC.isPresent()) {
			 tabC.setTotalAmount(transactionData.getTransactionAmount() + tableC.get().getTotalAmount());
			 tabC.setUserId(transactionData.getUserId());
			 tableCRepository.save(tabC);
		 } else {
			 
			 tabC.setUserId(transactionData.getUserId());
			 tabC.setTotalAmount(transactionData.getTransactionAmount());
			 tableCRepository.save(tabC);
		 }
		 return tableB;
		
	}
	
	

}
