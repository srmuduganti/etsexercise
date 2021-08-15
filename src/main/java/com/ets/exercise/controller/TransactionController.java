package com.ets.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.exercise.dto.Balance;
import com.ets.exercise.dto.TransactionData;
import com.ets.exercise.service.TransactionalService;

@RestController
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired
	TransactionalService TransactionalService;

	@GetMapping(path = "/blance/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getLastTransaction(@PathVariable("userId") String userId){
		try {
			Balance balance = TransactionalService.fetchBalanceByUserId(userId);
			if(null == balance) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found");
			}
			return new ResponseEntity<> (balance, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}
	
	@PostMapping(path =  "/transaction", consumes  = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity depositOrWithDraw(@RequestBody TransactionData transactionData){
		try {
			Balance balance = TransactionalService.makeTransaction(transactionData);
			return new ResponseEntity<>("Transaction is Success", HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
		
	}
}
