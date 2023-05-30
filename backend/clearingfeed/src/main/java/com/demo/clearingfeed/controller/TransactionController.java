package com.demo.clearingfeed.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.hibernate.resource.beans.internal.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.clearingfeed.service.FileService;
import com.demo.clearingfeed.service.TransactionService;
import com.demo.clearingfeed.entity.Transaction;
import com.demo.clearingfeed.entity.File;

@RestController
@CrossOrigin("*")
public class TransactionController {

	
	@Autowired
	private TransactionService transactionservice;
	@Autowired
	private FileService fileService;
	
	ArrayList<ArrayList<Transaction>> validatedTransactions = new ArrayList<ArrayList<Transaction>>();

	@PostMapping("/transaction/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
		String fileName = file.getOriginalFilename();
		this.transactionservice.save(file);
		try {
			Path path = Paths.get("src\\main\\resources\\archiveFolder\\"+fileName).toAbsolutePath();
			file.transferTo(path.toFile());
			validatedTransactions = transactionservice.validate((ArrayList<Transaction>) transactionservice.getAllTransactions());
			
			long millis=System.currentTimeMillis();  
	        Date dateObj = new Date(millis);
	 		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	 		String currentDate = formatter.format(dateObj);
	 		
	 		File newFile=new File(fileName,currentDate,"Validated");
			this.fileService.save(newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(Map.of("message","file is uploaded data is saved to db"));
		
		}

	
	@GetMapping("/GetValidTransactions")
	public ArrayList<Transaction> getValidTransactions()
	{
		if(validatedTransactions.isEmpty())
		{
			validatedTransactions.add(new ArrayList<Transaction>());
			validatedTransactions.add(new ArrayList<Transaction>());
		}
			
		return validatedTransactions.get(0);
	}
	
	@GetMapping("/GetInvalidTransactions")
	public ArrayList<Transaction> getInvalidTransactions()
	{
		if(validatedTransactions.isEmpty())
		{
			validatedTransactions.add(new ArrayList<Transaction>());
			validatedTransactions.add(new ArrayList<Transaction>());
		}
			
		return validatedTransactions.get(1);
	}
	
	@GetMapping("/GetFileStatus")
	public List<File> getFileStatus()
	{
		return this.fileService.getAllFiles();
	}
}

