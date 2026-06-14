package com.example.membershipProgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.membershipProgram.model.PricingCatalogue;
import com.example.membershipProgram.service.impl.PriceCatalogueService;

@RestController
@RequestMapping("/api/v1/catalogue")
public class PriceCatalogueController {

    @Autowired
    private PriceCatalogueService pricingCatalogueService;

    @GetMapping("/${id}")
    public ResponseEntity<PricingCatalogue> getUserById(@PathVariable Long id) {
        PricingCatalogue user = pricingCatalogueService.getPriceCatalogueById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PricingCatalogue> addUser(@RequestBody PricingCatalogue user) {
        PricingCatalogue userCreated = pricingCatalogueService.addPriceCatalogue(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PricingCatalogue> updateUser(@RequestBody PricingCatalogue user, @PathVariable Long id) {
        PricingCatalogue userCreated = pricingCatalogueService.updatePriceCatalogue(user, id);
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PricingCatalogue> deleteUserById(@PathVariable Long id) {
        pricingCatalogueService.deletePriceCatalogueById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
