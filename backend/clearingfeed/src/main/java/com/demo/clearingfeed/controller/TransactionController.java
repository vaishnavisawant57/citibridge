package com.demo.clearingfeed.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.resource.beans.internal.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.demo.clearingfeed.service.TransactionService;

import com.demo.clearingfeed.entity.Transaction;
@RestController
@CrossOrigin("*")
public class TransactionController {

	
	@Autowired
	private TransactionService transactionservice;
	
	@PostMapping("/transaction/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
		this.transactionservice.save(file);
		return ResponseEntity.ok(Map.of("message","file is uploaded data is saved to db"));
	}
	
	@GetMapping("/transaction")
	public List<Transaction> getAllProduct()
	{
		return this.transactionservice.getAllTransactions();
	}
	
}

