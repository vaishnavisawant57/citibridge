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
	private int fileCount=1;
	ArrayList<ArrayList<Transaction>> validatedTransactions = new ArrayList<ArrayList<Transaction>>();

	@PostMapping("/transaction/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
		String fileName = file.getOriginalFilename();
		this.transactionservice.save(file);
		try {
			Path path = Paths.get("src\\main\\resources\\archiveFolder\\"+fileName).toAbsolutePath();
			file.transferTo(path.toFile());
			validatedTransactions = transactionservice.validate((ArrayList<Transaction>) transactionservice.getAllTransactions());
			System.out.print(validatedTransactions);
			long millis=System.currentTimeMillis();  
	        Date dateObj = new Date(millis);
	 		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	 		String currentDate = formatter.format(dateObj);
	 		
	 		File newFile=new File(fileCount,fileName,currentDate,"Validated");
	 		fileCount++;
			this.fileService.save(newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(Map.of("message","File validated successfully"));
		}

	
	@GetMapping("/GetValidTransactions")
	public ArrayList<Transaction> getValidTransactions()
	{
		if(validatedTransactions.isEmpty())
		{
			validatedTransactions.add(new ArrayList<Transaction>());
			validatedTransactions.add(new ArrayList<Transaction>());
		}
//		System.out.print(validatedTransactions.get(0));
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
//		System.out.print(validatedTransactions.get());
		return validatedTransactions.get(1);
	}
	
	@GetMapping("/GetFileStatus")
	public List<File> getFileStatus()
	{
		return this.fileService.getAllFiles();
	}
	@GetMapping("/GetTransNumberPerReason")
	public ArrayList<Integer> getTransactionCountPerReason()
	{
		ArrayList<Integer> transactionCount = new ArrayList<Integer>();
		for(int i=0;i<9;i++)
		{
			transactionCount.add(0);
		}
		int numberOfValid = validatedTransactions.isEmpty() ? 0 : validatedTransactions.get(0).size();
		int numberOfInvalid = validatedTransactions.isEmpty() ? 1 : validatedTransactions.get(1).size();
		
		for(int i=0;i<validatedTransactions.get(1).size();i++)
		{
			if(validatedTransactions.get(1).get(i).getReason().equals("Invalid Reference Number"))
				transactionCount.set(0, transactionCount.get(0)+1);
			else if(validatedTransactions.get(1).get(i).getReason().equals("Invalid Transaction Date"))
				transactionCount.set(1, transactionCount.get(1)+1);
			else if(validatedTransactions.get(1).get(i).getReason().equals("Invalid Payer Name"))
				transactionCount.set(2, transactionCount.get(2)+1);
			else if(validatedTransactions.get(1).get(i).getReason().equals("Invalid Payer Account"))
				transactionCount.set(3, transactionCount.get(3)+1);
			else if(validatedTransactions.get(1).get(i).getReason().equals("Invalid Payee Name"))
				transactionCount.set(4, transactionCount.get(4)+1);
			else if(validatedTransactions.get(1).get(i).getReason().equals("Invalid Payee Account"))
				transactionCount.set(5, transactionCount.get(5)+1);
			else 
				transactionCount.set(6, transactionCount.get(6)+1);
		}
		
		
		transactionCount.set(7, numberOfValid);
		transactionCount.set(8, numberOfInvalid);
		
		return transactionCount;
	}
}