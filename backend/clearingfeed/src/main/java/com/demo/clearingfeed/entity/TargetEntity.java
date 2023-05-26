package com.demo.clearingfeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TargetEntity {
	@Id
	private String transaction_ref_no;
	private String date;
	private String payer_account;
	private String payer_name;
	private String payee_account;
	private String payee_name;
	private String amount;
	
	public TargetEntity(String transaction_ref_no, String date, String payer_account, String payer_name,
			String payee_account, String payee_name, String amount) {
		
		this.transaction_ref_no = transaction_ref_no;
		this.date = date;
		this.payer_account = payer_account;
		this.payer_name = payer_name;
		this.payee_account = payee_account;
		this.payee_name = payee_name;
		this.amount = amount;
	}
	
	public TargetEntity() {
		
		
	}

	public String getTransaction_ref_no() {
		return transaction_ref_no;
	}

	public void setTransaction_ref_no(String transaction_ref_no) {
		this.transaction_ref_no = transaction_ref_no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPayer_account() {
		return payer_account;
	}

	public void setPayer_account(String payer_account) {
		this.payer_account = payer_account;
	}

	public String getPayer_name() {
		return payer_name;
	}

	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}

	public String getPayee_account() {
		return payee_account;
	}

	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}

	public String getPayee_name() {
		return payee_name;
	}

	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String account) {
		this.amount = account;
	}
	
	
}
