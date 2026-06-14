package com.example.membershipProgram.state;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.SubscriptionStatus;

public class CancelledSubscriptionState implements ISubscriptionState{
    @Override
    public void onCancel(Subscription subscription) {
       throw new IllegalStateException("Membership is already cancelled");
    }

    @Override
    public void onExpire(Subscription subscription) {
        subscription.setStatus(SubscriptionStatus.EXPIRED);
    }

    @Override
    public void onRenew(Subscription subscription, PlanType planType) {
        throw new IllegalStateException("Cannot renew a cancelled subcription.");
    }
}
