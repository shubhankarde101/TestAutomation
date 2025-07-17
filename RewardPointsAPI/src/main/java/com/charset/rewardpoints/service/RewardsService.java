package com.charset.rewardpoints.service;

import com.charset.rewardpoints.model.Rewards;



public interface RewardsService {
    public Rewards getRewardsByCustomerId(Integer customerId);
}
