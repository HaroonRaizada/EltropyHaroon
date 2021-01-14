package com.eltropy.assignment.entity;

import java.util.*;

import javax.persistence.*;

import org.springframework.stereotype.Component;
 
@Entity
@Component
@Table(name = "users")

public class Employee {
	
	 
	    @Id
	    @Column(name = "user_id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    private String username;
	    private String password;
	    private boolean enabled;
	    
	   
	     
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "users_roles",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id")
	            )
	    private Set<EmployeeRole> roles = new HashSet<>();
	 
	    public Long getId() {
	        return id;
	    }

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

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public Set<EmployeeRole> getRoles() {
			return roles;
		}

		public void setRoles(Set<EmployeeRole> roles) {
			this.roles = roles;
		}

		public void setId(Long id) {
			this.id = id;
		}
	 
	  
	
}
