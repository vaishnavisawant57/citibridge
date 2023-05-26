package com.demo.clearingfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.clearingfeed.entity.TargetEntity;
import com.demo.clearingfeed.entity.Transaction;
import com.demo.clearingfeed.repo.TransactionRepo;
import com.demo.clearingfeed.service.TargetService;
import com.demo.clearingfeed.service.TransactionService;


@RestController
@RequestMapping("/api")

public class CopyController {
	

	    private final TargetService targetService;
	    private final TransactionRepo transactionRepository;
	    private final TransactionService transactionService;
	    
	    public CopyController(TargetService targetService,TransactionRepo transactionRepository,TransactionService transactionService) {
	        this.targetService = targetService;
	        this.transactionRepository=transactionRepository;
	        this.transactionService=transactionService;
	    }

	    @PostMapping("/copy")
	    @Transactional
	    public ResponseEntity<String> copyTransactions() {
	       // targetService.copyTransactions();
	       // return ResponseEntity.ok("Data copied successfully");
	        List<Transaction> transactions = transactionService.getAllTransactions();
	        List<TargetEntity> targets = targetService.copyTransactions(transactions);
	        targetService.saveAll(targets);

	        
	          transactionRepository.deleteAllTransactions();

        return ResponseEntity.ok("Transactions copied and deleted successfully.");
	    }
	
	

	}

