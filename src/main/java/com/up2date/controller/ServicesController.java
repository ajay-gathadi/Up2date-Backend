package com.up2date.controller;

import com.up2date.entity.Service;
import com.up2date.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "*")
public class ServicesController {

    private final ServiceRepository serviceRepository;

    public ServicesController(ServiceRepository serviceRepository){
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public List<Service> getAllServicesOrderByUsageCount(){
        return serviceRepository.findAllOrderByServiceUsageCount();
    }

    @GetMapping("/servicesByGender/{gender}")
    public List<Service> getServicesByGender(@PathVariable String gender){
        if("MALE".equalsIgnoreCase(gender)){
            return serviceRepository.findByForMaleIsTrue();
        }else {
            return serviceRepository.findByForFemaleIsTrue();
        }
    }

    @PostMapping("/services")
    public Service createService(@Valid @RequestBody Service service) {
        return serviceRepository.save(service);
    }
}
