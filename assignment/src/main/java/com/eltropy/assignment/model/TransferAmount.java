package com.eltropy.assignment.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eltropy.assignment.entity.EmployeeRole;

@Component
public class TransferAmount {
	private Long toAccountId;
	public Long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}
	public Long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public Double getAmountTransferred() {
		return amountTransferred;
	}
	public void setAmountTransferred(Double amountTransferred) {
		this.amountTransferred = amountTransferred;
	}
	private Long fromAccountId;
	private Double amountTransferred;

}
