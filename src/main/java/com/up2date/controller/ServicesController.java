package com.up2date.controller;

import com.up2date.entity.Service;
import com.up2date.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "*")
public class ServicesController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/servicesByGender/{gender}")
    public List<Service> getServicesByGender(@PathVariable String gender){
        if("MALE".equalsIgnoreCase(gender)){
            return serviceRepository.findByForMaleIsTrue();
        }else {
            return serviceRepository.findByForFemaleIsTrue();
        }
    }

    @PostMapping
    public Service createService(@Valid @RequestBody Service service) {
        return serviceRepository.save(service);
    }
}
