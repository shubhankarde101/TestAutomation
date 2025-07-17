package com.charset.rewardpoints.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charset.rewardpoints.entity.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    public List<Transaction> findAllByCustomerId(Integer customerId);
}
