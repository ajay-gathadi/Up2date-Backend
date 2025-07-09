package com.up2date.service;

import com.up2date.entity.CustomerService;
import com.up2date.repository.CustomerServiceRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommissionBackFillService {
    private static final Logger logger = LoggerFactory.getLogger(CommissionBackFillService.class);
    private final CustomerServiceRepository customerServiceRepository;

    public CommissionBackFillService(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    @PostConstruct
    public void backfillCommision(){
        logger.info("Cheking for historical records that need commission backfilled...");
        List<CustomerService> commisionToUpdate = customerServiceRepository.findByCommissionIsNull();

        if(commisionToUpdate.isEmpty()){
            logger.info("No Records found for the commision update for the old data");
            return;
        }

        logger.info("Found {} records to update. Processing now...", commisionToUpdate);

        for(CustomerService oldData : commisionToUpdate){
            if(oldData.getCSEmployeeId() != null && oldData.getAmount() != null){
                double commision = oldData.getAmount() * 0.10;
                oldData.setCommission(commision);
            } else {
                oldData.setCommission(0.0);
            }
        }

        customerServiceRepository.saveAll(commisionToUpdate);
        logger.info("Successfully backfilled commission for {} records", commisionToUpdate.size());
    }
}
