package com.example.membershipProgram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.TierType;

@Repository
public interface PricingCatalogueRepository extends JpaRepository<PricingCatalogue, Long>{

    Optional<PricingCatalogue> findByPlanTypeAndTierType(PlanType planType, TierType tierType);
}
