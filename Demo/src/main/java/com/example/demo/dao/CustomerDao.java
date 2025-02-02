package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CustomerDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerRepository custRepository;

	@Autowired
	public Customer customer;
	

	@Transactional
	public Customer saveCustomer(Customer cust) {
	System.out.println("DB call --->"+cust);	
	//empRepository.save(emp);
	
	return custRepository.save(cust);
	
	
	}


	public Customer fetchDbdetails(long custId) throws Exception {

		try {
			Optional<Customer> cust = custRepository.findById(custId);
			System.out.println("Customer Details --> "+cust.toString());
			if(cust != null)
			{
				return cust.get();	
			}else {
				return customer;
			}
			
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Incorrect custid requested to fech Db details --> "+custId);
		}
		catch(Exception ex) {
			throw new Exception("Exception occured -->"+ex.getMessage());
		}
		
		
	}

}
