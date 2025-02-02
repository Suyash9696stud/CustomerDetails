package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerResponse;
import com.example.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	
	
	@PostMapping(value = "/CustomerDetails")
	public ResponseEntity<CustomerResponse> getStatus(@RequestBody Customer custBody) throws ValidationException{
		CustomerResponse customerResponse = new CustomerResponse();
	 try{
		 customerService.invokeCustomerDetails(custBody);
		 customerResponse.setCustomerId(String.valueOf(custBody.getCustId()));
		 customerResponse.setMessage("Customer details saved successfully");
			return new ResponseEntity<>(customerResponse,HttpStatus.OK);
	 }catch (ValidationException e){
		 customerResponse.setCustomerId("customer validation Error for:"+String.valueOf(custBody.getCustId()));
		 customerResponse.setMessage(e.getMessage());
		 return new ResponseEntity<>(customerResponse,HttpStatus.BAD_REQUEST);
	 }
	 catch (Exception ex){
		 customerResponse.setCustomerId("Error:"+String.valueOf(custBody.getCustId()));
		 customerResponse.setMessage(ex.getMessage());
		 return new ResponseEntity<>(customerResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	 }
		
	}
	
	@GetMapping(value = "/fetch")
	public  ResponseEntity<Customer> getCustomer(@RequestParam long custId) throws Exception{
		
		try {
			return customerService.fetchCustomerDetails(custId);
			
		}catch(IllegalArgumentException e){
			
			
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return null;
		
		
		
	}
 
	
	
}
