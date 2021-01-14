package com.eltropy.assignment.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eltropy.assignment.entity.Accounts;
import com.eltropy.assignment.entity.Customer;

@Repository
public interface AccountsRepo  extends CrudRepository<Accounts, Integer> {
	 @Query("SELECT u FROM Accounts u WHERE u.id = :id")
	    public Accounts getUserByAccountId(@Param("id") Long id);
	 
	 Set<Accounts> findBycust(Customer customer);
	 @Query(value = "SELECT u from  Accounts u WHERE u.accountStartDate >= :startDate AND u.accountStartDate <= :endDate")
	 List<Accounts> getAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}