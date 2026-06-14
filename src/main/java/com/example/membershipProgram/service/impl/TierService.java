package com.example.membershipProgram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.enums.SubscriptionStatus;
import com.example.membershipProgram.model.enums.TierType;
import com.example.membershipProgram.repository.PricingCatalogueRepository;
import com.example.membershipProgram.repository.SubscriptionRepository;
import com.example.membershipProgram.repository.UserRepository;
import com.example.membershipProgram.service.ITierSubscriptionService;

@Service
public class TierService implements ITierSubscriptionService    {

    @Autowired
    private PricingCatalogueRepository pricingCatalogueRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Subscription upgradeTier(Long userId, TierType newTierType) {
        /*
            Only active subscribers can change the tier and the ones not at higher tier already
        */
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            System.out.println("User must not be null!");
            return null;
        }
        if(!canUpgradeTier(user, newTierType)) return null;


        Subscription sub = user.getCurrentSubscription();
        double currentPrice = sub.getPricingCatalogue().getPrice();
        double diff = 0;

        PricingCatalogue newPricingCatalogue = pricingCatalogueRepository.findByPlanTypeAndTierType(sub.getPricingCatalogue().getPlanType(),
            newTierType).orElse(null);
        if(currentPrice > newPricingCatalogue.getPrice()) {
            // User should be refunded as choosing a cheaper plan....
            } else {
                diff = newPricingCatalogue.getPrice() - currentPrice;
                // User have to pay this extra diff
            }

            sub.setPricingCatalogue(newPricingCatalogue);
            return subscriptionRepository.save(sub);
    }

    @Override
    public boolean canUpgradeTier(User user, TierType tierType) {
        
        Subscription sub = user.getCurrentSubscription();
        if(sub == null) {
            System.out.println("User hasn't subscribed to any plan!");

            System.out.println("Redirecting to Subscribe....");
            return false;
        }
        if(sub.getStatus() != SubscriptionStatus.ACTIVE) {
            System.out.println("Can't change plan, Current Subscription is not active");
            return false;
        }
        if(sub.getPricingCatalogue().getTierType() == TierType.PLATINUM) {
            System.out.println("Cannot upgrade Tier, already subscribed to platinum");
            return false;
        }
        return true;
    }
}
