package com.charset.rewardpoints.controller;

import com.charset.rewardpoints.entity.Customer;
import com.charset.rewardpoints.repository.CustomerRepository;
import com.charset.rewardpoints.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charset.rewardpoints.model.Rewards;

@RestController
@RequestMapping("/customers")
public class RewardsController {
    @Autowired
    RewardsService rewardsService;
    @Autowired
    CustomerRepository customerRepository;
    // Hit this Endpoint to get the desired result
    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Integer customerId){
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
        	throw new RuntimeException("Customer not available");
        }
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRewards);
    }

}
