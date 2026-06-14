package com.example.membershipProgram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.SubscriptionStatus;
import com.example.membershipProgram.model.enums.TierType;
import com.example.membershipProgram.repository.PricingCatalogueRepository;
import com.example.membershipProgram.repository.SubscriptionRepository;
import com.example.membershipProgram.service.IPlanSubscriptionService;

@Service
public class PlanService implements IPlanSubscriptionService{

    @Autowired
    private PricingCatalogueRepository pricingCatalogueRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription changePlan(User user, PlanType newPlanType) {        
        /*
            Only active subscribers can change the plan with same Tier
        */
       Subscription sub = user.getCurrentSubscription();
       if(!canChangePlan(sub)) return null;

       double currentPrice = sub.getPricingCatalogue().getPrice();
       double diff = 0;

       PricingCatalogue newPricingCatalogue = findPricingCatalogue(sub.getPricingCatalogue().getTierType(), newPlanType);
       if(currentPrice > newPricingCatalogue.getPrice()) {
        // User should be refunded as choosing a cheaper plan....
        } else {
            diff = newPricingCatalogue.getPrice() - currentPrice;
            // User have to pay this extra diff
        }

        sub.setPricingCatalogue(newPricingCatalogue);
        return subscriptionRepository.save(sub);
    }

    private PricingCatalogue findPricingCatalogue(TierType tierType, PlanType planType) {
        return pricingCatalogueRepository.findByPlanTypeAndTierType(planType,
            tierType).orElse(null);
    }

    private boolean canChangePlan(Subscription sub) {
        if(sub == null) {
            System.out.println("User hasn't subscribed to any plan!");

            System.out.println("Redirecting to Subscribe....");
            return false;
       }
       if(sub.getStatus() != SubscriptionStatus.ACTIVE) {
            System.out.println("Can't change plan, Current Subscription is not active");
            return false;
       }
       return true;
    }
}
