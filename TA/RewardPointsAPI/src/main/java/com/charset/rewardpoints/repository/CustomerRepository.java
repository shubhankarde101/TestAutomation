package com.charset.rewardpoints.repository;

import org.springframework.data.repository.CrudRepository;

import com.charset.rewardpoints.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    public Customer findByCustomerId(Integer customerId);
}
