package com.example.membershipProgram.service;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.enums.TierType;

public interface ITierSubscriptionService {
    Subscription upgradeTier(User user, TierType tierType);
    boolean canUpgradeTier(User user, TierType tierType);
}
