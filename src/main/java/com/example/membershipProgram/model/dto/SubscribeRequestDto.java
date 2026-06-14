package com.example.membershipProgram.model.dto;

import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.TierType;

import lombok.Getter;

@Getter
public class SubscribeRequestDto {
    private PlanType planType;
    private TierType tierType;
}
