package com.example.demo.model;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.*;
import lombok.*;



@Table(name = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long custId;
	
	@Column(name = "first_name" )
	private String firstName; 
	

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

	@Column(name = "last_name" )
	private String lastName;
	
	@Column(name = "date_of_birth" )
	private Date dateOfBirth;
	
//	@Version
//	private Integer version = 0;
	
	/*
	 * public Customer(String firstName, String lastName ) { this.firstName =
	 * firstName; this.lastName = lastName; }
	 * 
	 * public Customer() {
	 * 
	 * }
	 */

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
		

}
