package com.demo.clearingfeed.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.clearingfeed.entity.Transaction;

public interface TransactionService {
	public void save(MultipartFile file);
	public List<Transaction> getAllTransactions();
}
