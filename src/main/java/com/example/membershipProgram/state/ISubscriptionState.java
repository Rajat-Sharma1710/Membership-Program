package com.example.membershipProgram.state;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.enums.PlanType;

public interface ISubscriptionState {
    void onCancel(Subscription subscription);
    void onExpire(Subscription subscription);
    void onRenew(Subscription subscription, PlanType planType);
}
