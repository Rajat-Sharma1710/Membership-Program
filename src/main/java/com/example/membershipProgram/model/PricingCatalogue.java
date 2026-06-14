package com.example.membershipProgram.model;

import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.TierType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pricing_catalogue")
@Getter
@Setter
@NoArgsConstructor
public class PricingCatalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private PlanType planType;
    @Enumerated(EnumType.STRING)
    private TierType tierType;
    private double price;
}
