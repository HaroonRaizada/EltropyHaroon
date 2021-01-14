package com.eltropy.assignment.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eltropy.assignment.entity.Accounts;
import com.eltropy.assignment.entity.Customer;
import com.eltropy.assignment.entity.Employee;
import com.eltropy.assignment.entity.ReservedTokens;

@Repository
public interface ReservedTokenRepo  extends CrudRepository<ReservedTokens, String> {
	
	 @Query("SELECT u FROM ReservedTokens u WHERE u.token = :token")
	    public ReservedTokens getReservedToken(@Param("token") String token);
}