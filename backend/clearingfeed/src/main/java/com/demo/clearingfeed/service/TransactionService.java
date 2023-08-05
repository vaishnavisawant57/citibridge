package com.demo.clearingfeed.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat; 
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.clearingfeed.entity.TargetEntity;
import com.demo.clearingfeed.entity.Transaction;
import com.demo.clearingfeed.helper.Helper;
import com.demo.clearingfeed.repo.TargetRepository;
import com.demo.clearingfeed.repo.TransactionRepo;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepo transactionRepo;
	private TargetService targetService;
	List<TargetEntity> prevTransactions=new ArrayList<TargetEntity>();
	@Autowired
	private TargetRepository targetRepo;

	public void save(MultipartFile file) 
	{
		try {
			List<Transaction> transaction = Helper.ConvertExcelToListOftransaction(file.getInputStream());
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
	
	public boolean isValid(Transaction transaction) {

		// TODO validate each parameter of a transaction object 
		String refNo = transaction.getTransaction_ref_no();
		String date = transaction.getDate();
		String payerAccount = transaction.getPayer_account();
		String payerName = transaction.getPayer_name();
		String payeeAccount = transaction.getPayee_account();
		String payeeName = transaction.getPayee_name();
		double amount = Double.parseDouble(transaction.getAmount());
		
		//prevTransactions=targetService.getAllTransactions();

		//validate if refNo is unique 
		for(TargetEntity i:prevTransactions)
		{
			if(i.getTransaction_ref_no()==refNo)
			{
				System.out.println("Invalid Reference Number");
				return false;
			}
		}		
		
		//validate refno length
		Pattern pattern1 = Pattern.compile("\\w{12}");
		Matcher matcher1 = pattern1.matcher(refNo);
		if(!(matcher1.matches()))
		{
			System.out.println(refNo);
			System.out.println("Invalid Reference Number");
			return false;
		}

		//validate date
		long millis=System.currentTimeMillis();  
        Date dateObj = new Date(millis);
 		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 		String currentDate = formatter.format(dateObj);
 		
		if(!(currentDate.equals(date)))
		{
			System.out.println(date);
			System.out.println("Invalid Transaction Date");
			return false;
		}
			
		//validate payer name
		Pattern pattern2 = Pattern.compile("\\w{0,35}");
		Matcher matcher2 = pattern2.matcher(payerName);
		if(!(matcher2.matches()))
		{
			System.out.println(payerName);
			System.out.println("Invalid Payer Name");
			return false;
		}

		//validate payer account no
		Matcher matcher3 = pattern1.matcher(payerAccount);
		if(!(matcher3.matches()))
		{
			System.out.println(payeeAccount);
			System.out.println("Invalid Payer Account");
			return false;
		}
		
		//validate payee name
		Matcher matcher4 = pattern2.matcher(payeeName);
		if(!(matcher4.matches()))
		{
			System.out.println(payeeName);
			System.out.println("Invalid Payee Name");
			return false;
		}

		//validate payee account no
		Matcher matcher5 = pattern1.matcher(payeeAccount);
		if(!(matcher5.matches()))
		{
			System.out.println(payeeAccount);
			System.out.println("Invalid Payee Account");
			return false;
		}
		
		//validate amount
		String regex ="[0-9]{0,10}\\.[0-9]{0,2}";
		Pattern pattern3 = Pattern.compile(regex);
		String amounts = new DecimalFormat("#.00#").format(amount);
		Matcher matcher6 = pattern3.matcher(amounts);
		if(!matcher6.matches() || amount<0)
		{
			System.out.println(amount);
			System.out.println("Invalid Amount");
			return false;
		}
		
		return true;
	}
	
	public ArrayList<ArrayList<Transaction>> validate(ArrayList<Transaction> allTransactions) throws IOException{

		//create 2 arraylists - valid and invalid transactions
		ArrayList<ArrayList<Transaction>> validatedTransactions=new ArrayList<ArrayList<Transaction>>();
		ArrayList<Transaction> validTransactions = new ArrayList<Transaction>();
		ArrayList<Transaction> invalidTransactions = new ArrayList<Transaction>();
	
		for(int i=1;i<allTransactions.size();i++)
		{
			Transaction transaction=allTransactions.get(i);
			if(isValid(transaction))
			{
				//validate refNo - unique in current file 
				boolean flag = true;
				for(int j=0;j<i;j++)
				{
					if(transaction.getTransaction_ref_no().equals(allTransactions.get(j).getTransaction_ref_no()))
					{
						invalidTransactions.add(transaction);
						System.out.println("Invalid Reference Number");
						flag = false;
						break;
					}
				}
				if(flag)
					validTransactions.add(transaction);
			}
			else
			{
				invalidTransactions.add(transaction);
			}
		}
		validatedTransactions.add(validTransactions);
		validatedTransactions.add(invalidTransactions);
		System.out.print("Valid"+validatedTransactions.get(0));
		System.out.print("Invalid"+validatedTransactions.get(1));
		//save all valid transactions to database
		saveValidTransactions(validTransactions);
		return validatedTransactions;
	}
	
	@Transactional
	public void saveValidTransactions(ArrayList<Transaction> validTransactions) throws IOException{
		
		List<TargetEntity> list=new ArrayList<TargetEntity>();
		for(Transaction i: validTransactions)
		{
			TargetEntity te=new TargetEntity(i.getTransaction_ref_no(),i.getDate(),i.getPayer_account(),i.getPayer_name(),i.getPayee_account(),i.getPayee_name(),i.getAmount());
			list.add(te);
		}
		targetRepo.saveAll(list);
		transactionRepo.deleteAll();
	}
}
