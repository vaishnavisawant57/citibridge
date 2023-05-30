package com.demo.clearingfeed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.clearingfeed.entity.File;
import com.demo.clearingfeed.repo.FileRepo;


@Service
public class FileService {

	@Autowired
	private FileRepo fileRepo;
	
	public void save(File file) {
		this.fileRepo.save(file);
	}
	public List<File> getAllFiles(){
		return this.fileRepo.findAll();
	}
}
