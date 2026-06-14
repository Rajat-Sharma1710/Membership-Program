package com.example.membershipProgram.model;

import java.time.LocalDate;

import com.example.membershipProgram.model.enums.SubscriptionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription")
@Getter
@Setter
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "pricing_catalogue_id")
    private PricingCatalogue pricingCatalogue;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
