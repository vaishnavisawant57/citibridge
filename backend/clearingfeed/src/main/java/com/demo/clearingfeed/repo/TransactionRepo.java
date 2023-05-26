package com.demo.clearingfeed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.clearingfeed.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,String> {

	
}
