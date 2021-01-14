package com.eltropy.assignment.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="accounts")
public class Accounts {

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
    
    private Customer cust;
	
	@Id
    @Column(name = "acct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Double availableBal;
	
	private String accountType;
	
	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
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
