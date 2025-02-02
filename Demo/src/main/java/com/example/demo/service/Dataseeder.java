package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Dataseeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    public Dataseeder(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        customerRepository.saveAll(List.of(
                new Customer(1,"sunder","mehra",new Date()),
                new Customer(1,"sunder","mehra",new Date()),
                new Customer(1,"sunder","mehra",new Date())
        ));

        System.out.println("Sample student data inserted!");

    }
}
