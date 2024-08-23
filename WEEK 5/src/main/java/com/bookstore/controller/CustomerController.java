package com.bookstore.controller;

import com.bookstore.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestParam String name,
                                                   @RequestParam String email) {
        customers.add(new Customer(name, email));
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
    }
}

