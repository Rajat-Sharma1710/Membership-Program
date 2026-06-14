package com.example.membershipProgram.service;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.enums.PlanType;

public interface IPlanSubscriptionService {
    Subscription changePlan(Long userId, PlanType planType);
}