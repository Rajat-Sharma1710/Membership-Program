package com.example.membershipProgram.service;

import com.example.membershipProgram.model.Subscription;
import com.example.membershipProgram.model.User;
import com.example.membershipProgram.model.dto.SubscribeRequestDto;

public interface IUserService {

    Subscription subscribe(Long userId, SubscribeRequestDto subscribeRequestDto);

    Subscription cancelSubscription(Long userId);

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(User user, Long id);

    void deleteUserById(Long id);
}
