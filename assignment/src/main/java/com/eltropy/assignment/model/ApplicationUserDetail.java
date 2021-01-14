package com.eltropy.assignment.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eltropy.assignment.entity.Employee;
import com.eltropy.assignment.entity.EmployeeRole;

@AutoConfigurationPackage

public class ApplicationUserDetail implements UserDetails {
	@Autowired
	private Employee emp;
	public ApplicationUserDetail(Employee emp) {
        this.emp = emp;
    }
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		  Set<EmployeeRole> roles = emp.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (EmployeeRole role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	         
	        return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emp.getUsername();
	}

	
}
