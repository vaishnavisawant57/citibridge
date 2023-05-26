package com.demo.clearingfeed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.clearingfeed.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,String> {
	@Modifying
    @Query("DELETE FROM Transaction")
    void deleteAllTransactions();
	
}
