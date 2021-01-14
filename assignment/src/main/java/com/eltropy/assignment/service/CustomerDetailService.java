package com.eltropy.assignment.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eltropy.assignment.entity.Accounts;
import com.eltropy.assignment.entity.Customer;
import com.eltropy.assignment.model.AccountLinkage;
import com.eltropy.assignment.repository.AccountsRepo;
import com.eltropy.assignment.repository.CustomerRepo;

@Service
public class CustomerDetailService {
	@Autowired
	private AccountLinkage linkage;
	
	@Autowired
		private AccountsRepo acctRepo;
	@Autowired
	private CustomerRepo custRepo;

	
	
	public Customer loadCustomers(long customerId)  {
		// TODO Auto-generated method stub
		
	

		return custRepo.getUserByCustomerId(customerId);
		
	}

	public Customer saveCustomer(Customer user) {
		
		user.setDateOfBirth(LocalDate.now());
		return custRepo.save(user);
	}
	
public Accounts saveAccounts(Accounts user) {
		
		user.setAccountStartDate((LocalDate.now()));
		return acctRepo.save(user);
	}
public AccountLinkage linkAccountWithCustomer(long AcctId,long customerId) {
	Accounts accounts=acctRepo.getUserByAccountId(AcctId);
	Customer cust=custRepo.getUserByCustomerId(customerId);
	
	linkage.setAccountStartDate(accounts.getAccountStartDate());
	linkage.setAvailableBal(accounts.getAvailableBal());
	linkage.setAccountId(accounts.getId());
	linkage.setCustomerId(customerId);
	linkage.setAccountStatus(accounts.getAccountStatus());
	
	Set<Accounts> accountsSet=new HashSet<Accounts>();
	accountsSet.add(accounts);
	cust.setAccounts(accountsSet);
		 custRepo.save(cust);
		return linkage;
		//return empRepo.de
	}

public Customer updateKYC(long customerId,boolean isKycEnabled) {
	
	Customer cust=custRepo.getUserByCustomerId(customerId);
	cust.setIsKYCEnable(isKycEnabled);
		return custRepo.save(cust);
		
		//return empRepo.de
	}
public void deleteCustomer(long customerId) {
	
	Customer cust=custRepo.getUserByCustomerId(customerId);
	
		 custRepo.delete(cust);
		
		//return empRepo.de
	}


public void transferAmount(long toAcid,long fromAcId,double amount) {
	Accounts toAcct=acctRepo.getUserByAccountId(toAcid);
	Accounts fromcct=acctRepo.getUserByAccountId(fromAcId);
	toAcct.setAvailableBal(toAcct.getAvailableBal()+amount);
	if(amount>fromcct.getAvailableBal()) {
		System.out.println("error");
	}else {
	fromcct.setAvailableBal(fromcct.getAvailableBal()-amount);
	}
	
	acctRepo.save(toAcct);
	acctRepo.save(fromcct);
}

public Accounts princialAmount(Accounts account) {
	long id=account.getId();
	Accounts toAcct=acctRepo.getUserByAccountId(id);
	Double simpleInterest=toAcct!=null?(toAcct.getAvailableBal()*3.5)/100:0.0;
	toAcct.setAvailableBal(toAcct.getAvailableBal()+simpleInterest);
	
	return acctRepo.save(toAcct);
}

}
