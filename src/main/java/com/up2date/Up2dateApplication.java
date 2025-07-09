package com.up2date;

import com.up2date.entity.Customer;
import com.up2date.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Up2dateApplication {
    Logger logger = LoggerFactory.getLogger(Up2dateApplication.class);

    @Autowired
    private final CustomerRepository customerRepository;

    public Up2dateApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Up2dateApplication.class, args);
//        SpringApplicationBuilder builder = new SpringApplicationBuilder(Up2dateApplication.class);
//        builder.headless(false).run(args);
    }
}