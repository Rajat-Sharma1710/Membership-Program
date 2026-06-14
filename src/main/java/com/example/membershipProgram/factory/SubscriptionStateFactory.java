package com.example.membershipProgram.factory;


import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.state.ActiveSubscriptionState;
import com.example.membershipProgram.state.CancelledSubscriptionState;
import com.example.membershipProgram.state.ExpiredSubscriptionState;
import com.example.membershipProgram.state.ISubscriptionState;

public class SubscriptionStateFactory {

    public static ISubscriptionState getCurrentSubscriptionState(Subscription subscription) {
        switch(subscription.getStatus()) {
            case ACTIVE:
                return new ActiveSubscriptionState();
            case EXPIRED:
                return new ExpiredSubscriptionState();
            case CANCELLED:
                return new CancelledSubscriptionState();
            default:
                return null;
        }
    }
}
