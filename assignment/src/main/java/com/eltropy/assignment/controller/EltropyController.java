package com.eltropy.assignment.controller;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eltropy.assignment.entity.Accounts;
import com.eltropy.assignment.entity.Customer;
import com.eltropy.assignment.entity.Employee;
import com.eltropy.assignment.entity.ReservedTokens;
import com.eltropy.assignment.filterSecurity.TokenExtractUtility;
import com.eltropy.assignment.model.AccountLinkage;
import com.eltropy.assignment.model.EmployeeModel;
import com.eltropy.assignment.model.LoginResponse;
import com.eltropy.assignment.model.PdfInputModel;
import com.eltropy.assignment.model.SignOutModel;
import com.eltropy.assignment.model.TransferAmount;
import com.eltropy.assignment.repository.ReservedTokenRepo;

import com.eltropy.assignment.service.CustomerDetailService;
import com.eltropy.assignment.service.GeneratePdf;

import com.eltropy.assignment.service.UserDetailServiceChild;


@RestController
@CrossOrigin
public class EltropyController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ReservedTokenRepo repoToken;
@Autowired
private ReservedTokens tokenEnt;
@Autowired
private GeneratePdf generatePdf;
	@Autowired
	private TokenExtractUtility tokenUtility;

	@Autowired
	private UserDetailServiceChild userDetailsService;
	@Autowired
	private CustomerDetailService customerDetailsService;
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody EmployeeModel authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = tokenUtility.generateToken(userDetails);
		tokenEnt.setToken(token);
		repoToken.save(tokenEnt);
		return ResponseEntity.ok(new LoginResponse(token));
	}
	
	@RequestMapping(value = "/auth/signOut", method = RequestMethod.POST)
	public ResponseEntity<?> signOut(@RequestBody SignOutModel tokenDetail) throws Exception {
		tokenEnt=repoToken.getReservedToken(tokenDetail.getToken());
	 repoToken.delete(tokenEnt);

		return ResponseEntity.ok("Logged Out Succesfully.");
	}
	
	@RequestMapping(value = "/employee/newEmployee", method = RequestMethod.POST)
	public ResponseEntity<?> addNewEMployee(@RequestBody EmployeeModel user) throws Exception {

		
		Employee userDetails = userDetailsService.save(user);
		return ResponseEntity.ok(userDetails);
	}
	@RequestMapping(value = "/employee/deletedEmp", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNewEMployee(@RequestBody EmployeeModel user) throws Exception {

		try {
		 userDetailsService.delete(user.getEmployeeId());
		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok("Deleted Succesfully");
	}
	
	@RequestMapping(value = "/customer/newCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer) throws Exception {
		Customer cust=null;
		try {
		  cust=customerDetailsService.saveCustomer(customer);
		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok(cust);
	}
	@RequestMapping(value = "/account/addAccount", method = RequestMethod.PUT)
	public ResponseEntity<?> addAccount(@RequestBody Accounts account) throws Exception {

		try {
		account=	customerDetailsService.saveAccounts(account);
		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok(account);
	}
	
	@RequestMapping(value = "/account/linkAccount", method = RequestMethod.PUT)
	public ResponseEntity<?> linkAccount(@RequestBody Accounts accountCustDetails) throws Exception {
AccountLinkage  linkage=null;
		try {
		 linkage=customerDetailsService.linkAccountWithCustomer(accountCustDetails.getId(), accountCustDetails.getCust().getId());

		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok(linkage);
	}
	
	@RequestMapping(value = "/customer/deleteCustomer", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@RequestBody Customer customerDetails) throws Exception {

		try {
		customerDetailsService.deleteCustomer(customerDetails.getId());

		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok("Deleted Succesfully");
	}
	@RequestMapping(value = "/customer/updateKYC", method = RequestMethod.PUT)
	public ResponseEntity<?> updateKYC(@RequestBody Customer customerDetails) throws Exception {

		try {
		customerDetailsService.updateKYC(customerDetails.getId(), customerDetails.getIsKYCEnable());

		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok("updated Succesfully");
	}
	@RequestMapping(value = "/accounts/transfer", method = RequestMethod.PUT)
	public ResponseEntity<?> signOut(@RequestBody TransferAmount tamount) throws Exception {
		try {
			customerDetailsService.transferAmount(tamount.getToAccountId(), tamount.getFromAccountId(), tamount.getAmountTransferred());
		}catch (Exception e) {
			return ResponseEntity.ok("Error");
		}
		return ResponseEntity.ok("Transferred Succesfully.");
	}
	
	@RequestMapping(value = "/customer/customerDetails", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomerDetails(@RequestBody Customer customerDetails,@RequestHeader HttpHeaders headers) throws Exception {
Customer cust=null;
		try {
		cust=customerDetailsService.loadCustomers(customerDetails.getId());

		}catch (Exception e) {
		return ResponseEntity.ok(e.getLocalizedMessage());
		}
		 return ResponseEntity.ok(cust);
	}
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	@RequestMapping(value = "/accounts/pdfreport", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_PDF_VALUE)
			public ResponseEntity<?> generatePrintStatement(@RequestBody PdfInputModel pdfInputModel) {

			

			java.io.ByteArrayInputStream bis = generatePdf.pdfReport(LocalDate.parse(pdfInputModel.getStartDate()), LocalDate.parse(pdfInputModel.getEndDate()));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=accountReport.pdf");

			return ResponseEntity
			    .ok()
			    .headers(headers)
			    .contentType(MediaType.APPLICATION_PDF)
			    .body(new InputStreamResource(bis));
			}
	@RequestMapping(value = "/accounts/interestAmount", method = RequestMethod.PUT)
			public ResponseEntity<?> calculatePrincipalAmount(@RequestBody Accounts account) {
try {
		 customerDetailsService.princialAmount(account);
}catch (Exception e) {
	ResponseEntity.ok("Bad request");
}		
			return ResponseEntity.ok("Balance updated");

	}	
}
