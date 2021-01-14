package com.eltropy.assignment.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eltropy.assignment.entity.EmployeeRole;

@Component
public class EmployeeModel {
	private String username;
	private String password;
	private long employeeId;
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	private List<EmployeeRole> roles;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<EmployeeRole> getRoles() {
		return roles;
	}
	public void setRoles(List<EmployeeRole> roles) {
		this.roles = roles;
	}

}
