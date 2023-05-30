package com.demo.clearingfeed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.clearingfeed.entity.File;
import com.demo.clearingfeed.entity.Transaction;

@Repository
public interface FileRepo extends JpaRepository<File,String>{
		
}
