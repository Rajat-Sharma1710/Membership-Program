package com.example.membershipProgram.state;

import com.example.membershipProgram.model.Subscription;

public interface ISubscriptionState {
    void onCancel(Subscription subscription);
    void onExpire(Subscription subscription);
    void onRenew(Subscription subscription);
}
