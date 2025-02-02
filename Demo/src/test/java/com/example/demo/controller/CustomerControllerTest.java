package com.example.demo.controller;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerResponse;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService; // Mocked Service

    @InjectMocks
    CustomerController customerController; // Controller with Mocked Service

    @Test
    void testGetStatus_Success() throws Exception {
        // Mock Customer Object
        Customer mockCustomer = new Customer(101L, "John", "Doe", null);// Sample ID

        // Mock Service Behavior (No Exception)
        doNothing().when(customerService).saveCustomerDetails(mockCustomer);

        // Call the method
        ResponseEntity<CustomerResponse> response = customerController.addCustomer(mockCustomer);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("101", response.getBody().getCustomerId());
        assertEquals("Customer details saved successfully", response.getBody().getMessage());

        // Verify Service Method was Called Once
        verify(customerService, times(1)).saveCustomerDetails(mockCustomer);
    }

    // **2. Test Case: ValidationException Handling**
    @Test
    void testGetStatus_ValidationException() throws Exception {
        // Mock Customer Object
        Customer mockCustomer = new Customer(102L, "John", "Doe", null);
        // Mock Service to Throw ValidationException
        doThrow(new ValidationException(HttpStatus.BAD_REQUEST,"Invalid customer data"))
                .when(customerService).saveCustomerDetails(mockCustomer);

        // Call the method
        ResponseEntity<CustomerResponse> response = customerController.addCustomer(mockCustomer);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("customer validation Error for:102", response.getBody().getCustomerId());

        // Verify Service Method was Called Once
        verify(customerService, times(1)).saveCustomerDetails(mockCustomer);
    }

    // **3. Test Case: General Exception Handling**
    @Test
    void testGetStatus_GeneralException() throws Exception {
        // Mock Customer Object
        Customer mockCustomer = new Customer(103L, "John", "Doe", null);

        // Mock Service to Throw General Exception
        doThrow(new RuntimeException("Unexpected error"))
                .when(customerService).saveCustomerDetails(mockCustomer);

        // Call the method
        ResponseEntity<CustomerResponse> response = customerController.addCustomer(mockCustomer);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Error:103", response.getBody().getCustomerId());
        assertEquals("Unexpected error", response.getBody().getMessage());

        // Verify Service Method was Called Once
        verify(customerService, times(1)).saveCustomerDetails(mockCustomer);
    }
}