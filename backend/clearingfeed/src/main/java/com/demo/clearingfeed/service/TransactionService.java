package com.demo.clearingfeed.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.clearingfeed.entity.Transaction;
import com.demo.clearingfeed.helper.Helper;
import com.demo.clearingfeed.repo.TransactionRepo;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepo transactionRepo;

	public void save(MultipartFile file) 
	{
		try {
			List<Transaction> transaction =Helper.ConvertExcelToListOftransaction(file.getInputStream());
			this.transactionRepo.saveAll(transaction);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Transaction> getAllTransactions()
	{
		return this.transactionRepo.findAll();
	}
	
	
}
