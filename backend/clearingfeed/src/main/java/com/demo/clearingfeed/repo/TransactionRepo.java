package com.demo.clearingfeed.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.demo.clearingfeed.entity.Transaction;
public interface TransactionRepo extends JpaRepository<Transaction,String> {
	public void save(MultipartFile file);
	public List<Transaction> getAllTransactions();
}
