package com.example.membershipProgram.state;

import java.time.LocalDate;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.SubscriptionStatus;

public class ActiveSubscriptionState implements ISubscriptionState{
    @Override
    public void onCancel(Subscription subscription) {
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        subscription.setEndDate(LocalDate.now());
    }

    @Override
    public void onExpire(Subscription subscription) {
        subscription.setStatus(SubscriptionStatus.EXPIRED);
        subscription.setEndDate(LocalDate.now());
    }

    @Override
    public void onRenew(Subscription subscription, PlanType planType) {
        LocalDate endDate = subscription.getEndDate();

        switch(planType) {
            case MONTHLY -> endDate = endDate.plusMonths(1);
            case QUARTERLY -> endDate = endDate.plusMonths(3);
            case YEARLY -> endDate = endDate.plusMonths(12);
        }

        subscription.setEndDate(endDate);
    }
}
