package com.demo.clearingfeed.service;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.*;


import com.demo.clearingfeed.entity.TargetEntity;
import com.demo.clearingfeed.entity.Transaction;
import com.demo.clearingfeed.repo.TargetRepository;
import com.demo.clearingfeed.repo.TransactionRepo;

@Service
public class TargetService {
	 private final TransactionRepo transactionRepository;
	    private final TargetRepository targetRepository;

	    public TargetService(TransactionRepo transactionRepository, TargetRepository targetRepository) {
	        this.transactionRepository = transactionRepository;
	        this.targetRepository = targetRepository;
	    }

	    //public void copyTransactions() {
	       public void saveAll(List<TargetEntity> targets) {
			// TODO Auto-generated method stub
	    	 targetRepository.saveAll(targets);
		}
	    public List<TargetEntity> copyTransactions(List<Transaction> transactions) {
	        List<TargetEntity> targets = new ArrayList<>();

	        for (Transaction transaction : transactions) {
	            TargetEntity targetEntity = new TargetEntity();
	            targetEntity.setTransaction_ref_no(transaction.getTransaction_ref_no());
	            targetEntity.setDate(transaction.getDate());
	            targetEntity.setPayer_account(transaction.getPayer_account());
	            targetEntity.setPayer_name(transaction.getPayer_name());
	            targetEntity.setAmount(transaction.getAmount());
	            targetEntity.setPayee_account(transaction.getPayee_account());
	            targetEntity.setPayee_name(transaction.getPayee_name());
	            
	            targets.add(targetEntity);
	        }

	       return  targetRepository.saveAll(targets);
	    }

		
	    
	 //   void saveAll(List<TargetEntity> targets);
}
