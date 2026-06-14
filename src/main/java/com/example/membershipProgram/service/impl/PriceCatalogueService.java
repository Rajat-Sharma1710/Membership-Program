package com.example.membershipProgram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.repository.PricingCatalogueRepository;
import com.example.membershipProgram.service.IPriceCatalogueService;

@Service
public class PriceCatalogueService implements IPriceCatalogueService{

    @Autowired
    private PricingCatalogueRepository pricingCatalogueRepository;
    @Override
    public PricingCatalogue getPriceCatalogueById(Long id) {
        return pricingCatalogueRepository.findById(id).orElse(null);
    }

    @Override
    public PricingCatalogue addPriceCatalogue(PricingCatalogue catalogue) {
        return pricingCatalogueRepository.save(catalogue);
    }

    @Override
    public PricingCatalogue updatePriceCatalogue(PricingCatalogue catalogue, Long id) {
        return pricingCatalogueRepository.save(catalogue);
    }

    @Override
    public void deletePriceCatalogueById(Long id) {
        pricingCatalogueRepository.deleteById(id);
    }

}
