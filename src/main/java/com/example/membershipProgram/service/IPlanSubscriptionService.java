package com.example.membershipProgram.service;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.enums.PlanType;

public interface IPlanSubscriptionService {
    Subscription changePlan(User user, PlanType planType);
}