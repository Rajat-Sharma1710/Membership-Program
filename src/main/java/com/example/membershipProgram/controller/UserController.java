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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.dto.SubscribeRequestDto;
import com.example.membershipProgram.model.enums.PlanType;
import com.example.membershipProgram.model.enums.TierType;
import com.example.membershipProgram.service.impl.PlanService;
import com.example.membershipProgram.service.impl.TierService;
import com.example.membershipProgram.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private TierService tierService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userCreated = userService.addUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User userCreated = userService.updateUser(user, id);
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/subscribe/{userId}")
    public ResponseEntity<Subscription> subscribe(@PathVariable Long userId, @RequestBody SubscribeRequestDto dto) {
        Subscription subscription = userService.subscribe(userId, dto);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @DeleteMapping("/cancelSubcription/{userId}")
    public ResponseEntity<Subscription> cancelSubcription(@PathVariable Long userId) {
        Subscription subscription = userService.cancelSubscription(userId);
        return ResponseEntity.ok(subscription);
    }

    @PutMapping("/changePlan/{userId}")
    public ResponseEntity<Subscription> changePlan(@PathVariable Long userId, @RequestParam PlanType planType) {
        Subscription subscription = planService.changePlan(userId, planType);
        return ResponseEntity.ok(subscription);
    }

    @PutMapping("/changeTier/{userId}")
    public ResponseEntity<Subscription> changeTier(@PathVariable Long userId, @RequestParam TierType tierType) {
        Subscription subscription = tierService.upgradeTier(userId, tierType);
        return ResponseEntity.ok(subscription);
    }

}
