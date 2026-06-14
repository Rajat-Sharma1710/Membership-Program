package com.example.membershipProgram.service;

import com.example.membershipProgram.model.PricingCatalogue;

public interface IPriceCatalogueService {

    PricingCatalogue getPriceCatalogueById(Long id);

    PricingCatalogue addPriceCatalogue(PricingCatalogue catalogue);

    PricingCatalogue updatePriceCatalogue(PricingCatalogue catalogue, Long id);

    void deletePriceCatalogueById(Long id);
}
