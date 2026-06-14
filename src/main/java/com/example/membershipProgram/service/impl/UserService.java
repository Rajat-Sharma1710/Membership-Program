package com.example.membershipProgram.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.enums.SubscriptionStatus;
import com.example.membershipProgram.repository.SubscriptionRepository;
import com.example.membershipProgram.repository.UserRepository;
import com.example.membershipProgram.service.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Subscription subscribe(User user, PricingCatalogue pricingCatalogue) {
        // First create a new Subscription and save it in a repo
        // then save user
        if(user.getCurrentSubscription() != null 
        && user.getCurrentSubscription().getStatus() == SubscriptionStatus.ACTIVE) {
            System.out.println("User already a member of FirstClub");
            return null;
        }

        Subscription subscription = new Subscription();
        subscription.setActive(true);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(calculateEndDate(pricingCatalogue));
        subscription.setPricingCatalogue(pricingCatalogue);
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        Subscription savedSub = subscriptionRepository.save(subscription);
        user.setCurrentSubscription(savedSub);
        user.getSubscriptionHistory().add(savedSub);

        userRepository.save(user);
        return savedSub;
    }

    @Override
    public Subscription cancelSubscription(User user) {
        if(user.getCurrentSubscription() == null) {
            System.out.println("No active subscription");
            return null;
        }

        Subscription currSubscription = user.getCurrentSubscription();
        subscriptionRepository.deleteById(currSubscription.getId());
        
        user.setCurrentSubscription(null);
        userRepository.save(user);
        return currSubscription;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private LocalDate calculateEndDate(PricingCatalogue pricingCatalogue) {
        LocalDate endDate = LocalDate.now();

        switch (pricingCatalogue.getPlanType()) {
            case MONTHLY:
                endDate = endDate.plusMonths(1);
                break;
            case QUARTERLY:
                endDate = endDate.plusMonths(4);
                break;
            case YEARLY:
                endDate = endDate.plusMonths(12);
                break;
            default:
                break;
        }

        return endDate;
    }
}
