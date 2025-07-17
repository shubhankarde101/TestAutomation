package com.charset.rewardpoints.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.charset.rewardpoints.entity.Transaction;
import com.charset.rewardpoints.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charset.rewardpoints.constants.Constants;
import com.charset.rewardpoints.model.Rewards;

@Service
public class RewardsServiceImpl implements RewardsService {
	@Autowired
    TransactionRepository transactionRepository;
	public Rewards getRewardsByCustomerId(Integer customerId) {
		// Finding the records filtered by customer Id in Transaction table
		List<Transaction> monthTransactions = transactionRepository.findAllByCustomerId(customerId);
		// Filtering the list of transactions based on first month data
		List<Transaction> month1Transactions = monthTransactions.stream().filter(transaction -> transaction.getTransactionDate().split("-")[1].equalsIgnoreCase("02"))
				.collect(Collectors.toList());
		// Filtering the list of transactions based on second month data
		List<Transaction> month2Transactions = monthTransactions.stream().filter(transaction -> transaction.getTransactionDate().split("-")[1].equalsIgnoreCase("03"))
				.collect(Collectors.toList());
		// Filtering the list of transactions based on third month data
		List<Transaction> month3Transactions = monthTransactions.stream().filter(transaction -> transaction.getTransactionDate().split("-")[1].equalsIgnoreCase("04"))
				.collect(Collectors.toList());
		//Calculating reward points
		Long lastMonthRewardPoints = getRewardsPerMonth(month1Transactions);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(month2Transactions);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(month3Transactions);
		// Setting up data in our model type Rewards after calculation
		Rewards customerRewards = new Rewards();
		customerRewards.setCustomerId(customerId);
		customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);
		return customerRewards;
	}
	private Long getRewardsPerMonth(List<Transaction> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}
	private Long calculateRewards(Transaction t) {
		if (t.getTransactionAmount() > Constants.firstRewardLimit && t.getTransactionAmount() <= Constants.secondRewardLimit) {
			return Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
		} else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
			return Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
					+ (Constants.secondRewardLimit - Constants.firstRewardLimit);
		} else
			return 0l;

	}
}
