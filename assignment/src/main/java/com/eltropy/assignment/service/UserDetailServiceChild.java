package com.eltropy.assignment.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eltropy.assignment.entity.Employee;
import com.eltropy.assignment.entity.EmployeeEntity;
import com.eltropy.assignment.model.ApplicationUserDetail;
import com.eltropy.assignment.model.EmployeeModel;
import com.eltropy.assignment.repository.EmployeeRepo;

@Service
public class UserDetailServiceChild implements UserDetailsService {
	@Autowired
		private EmployeeRepo empRepo;
@Autowired
private Employee empEntity;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public ApplicationUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
	

		Employee user = empRepo.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		ApplicationUserDetail applicationUserDetail=new ApplicationUserDetail(user);
		
		return  applicationUserDetail;
	}

	public Employee save(EmployeeModel user) {
		
		empEntity.setUsername(user.getUsername());
		empEntity.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		empEntity.setRoles(new HashSet(user.getRoles()));
		return empRepo.save(empEntity);
	}
public void delete(long employeeId) {
		
	Employee empl=empRepo.getUserById(employeeId);
		 empRepo.delete(empl);
		
		//return empRepo.de
	}
	
	
}
