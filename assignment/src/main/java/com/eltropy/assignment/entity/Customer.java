package com.eltropy.assignment.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Boolean getIsKYCEnable() {
		return isKYCEnable;
	}


	public void setIsKYCEnable(Boolean isKYCEnable) {
		this.isKYCEnable = isKYCEnable;
	}





	public Set<Accounts> getAccounts() {
		return accounts;
	}


	public void setAccounts(Set<Accounts> accounts) {
		this.accounts = accounts;
	}



	 @Column(name = "cust_name")
private String customerName;
	 @Column(name = "DOB")
	 
private LocalDate dateOfBirth;
	 @Column(name = "isKYCEnable")
private Boolean isKYCEnable;
	
	 
	 
	 @OneToMany(mappedBy = "cust", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    
private Set<Accounts> accounts = new HashSet<>();
}
