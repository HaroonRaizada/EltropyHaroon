package com.eltropy.assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eltropy.assignment.entity.Customer;

@Repository
public interface CustomerRepo  extends CrudRepository<Customer, Integer> {
	 @Query("SELECT u FROM Customer u WHERE u.id = :id")
	    public Customer getUserByCustomerId(@Param("id") Long id);
	
}