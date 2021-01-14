package com.eltropy.assignment.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class AccountLinkage {


    
    private long  customerId;
	
	
    private Long accountId;
	
	

	private Double availableBal;
	
	private String accountType;
	
	

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getAvailableBal() {
		return availableBal;
	}

	public void setAvailableBal(Double availableBal) {
		this.availableBal = availableBal;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public LocalDate getAccountStartDate() {
		return accountStartDate;
	}

	public void setAccountStartDate(LocalDate accountStartDate) {
		this.accountStartDate = accountStartDate;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	private LocalDate accountStartDate;
	
	private String accountStatus;
	
}
