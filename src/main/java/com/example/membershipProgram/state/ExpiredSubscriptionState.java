package com.example.membershipProgram.state;

import com.example.membershipProgram.model.Subscription;

public class ExpiredSubscriptionState implements ISubscriptionState{

    @Override
    public void onCancel(Subscription subscription) {
        throw new IllegalStateException("Cannot cancel. Membership is expired");    
    }

    @Override
    public void onExpire(Subscription subscription) {
        throw new IllegalStateException("Membership is already expired");
    }

    @Override
    public void onRenew(Subscription subscription) {
        throw new IllegalStateException("Cannot renew. Membership is expired");
    }
}
