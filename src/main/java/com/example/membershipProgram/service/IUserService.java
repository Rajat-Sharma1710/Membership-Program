package com.example.membershipProgram.service;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;

public interface IUserService {

    Subscription subscribe(User user, PricingCatalogue pricingCatalogue);

    Subscription cancelSubscription(User user);

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(User user, Long id);

    void deleteUserById(Long id);
}
