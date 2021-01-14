package com.eltropy.assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eltropy.assignment.entity.Employee;
import com.eltropy.assignment.entity.EmployeeEntity;

@Repository
public interface EmployeeRepo  extends CrudRepository<Employee, Integer> {
	 @Query("SELECT u FROM Employee u WHERE u.username = :username")
	    public Employee getUserByUsername(@Param("username") String username);
	 @Query("SELECT u FROM Employee u WHERE u.id = :employeeId")
	    public Employee getUserById(@Param("employeeId") Long employeeId);
}