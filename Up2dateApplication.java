package com.up2date;

import com.up2date.entity.Customer;
import com.up2date.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    }

//    @Bean
//    public CommandLineRunner initializeData(){
//        return args -> {
//            try{
//                customerRepository.save(new Customer("Geeta Gathadi", "9892703593", "Female"));
//                logger.info("Customer saved successfully");
//            }catch (Exception e){
//                logger.error("Error occurred while saving customer: {}", e.getMessage());
//            }
//        };
//    }
}
