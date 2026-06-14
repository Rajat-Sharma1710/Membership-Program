package com.example.membershipProgram.state;

import com.example.membershipProgram.model.Subscription;

public class ActiveSubscriptionState implements ISubscriptionState{
    @Override
    public void onCancel(Subscription subscription) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCancel'");
    }

    @Override
    public void onExpire(Subscription subscription) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onExpire'");
    }

    @Override
    public void onRenew(Subscription subscription) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onRenew'");
    }
}
