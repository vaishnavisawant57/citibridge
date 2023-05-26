package com.demo.clearingfeed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.clearingfeed.entity.TargetEntity;

@Repository
public interface TargetRepository extends JpaRepository<TargetEntity, String> {
	
}


