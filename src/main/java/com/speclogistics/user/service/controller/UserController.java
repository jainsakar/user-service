package com.speclogistics.user.service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.speclogistics.user.service.entity.User;
import com.speclogistics.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
@Slf4j
public class UserController {

    private final UserService userService;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping
    public Flux<User> getUsers() {
        return userService.getAll().doFirst(() -> log.info("Get All users request received"))
                .collectList().flatMapMany(userList -> {
                    log.info("found {} users on get users request", userList.size());
                    return Flux.fromIterable(userList);
                });
    }


    @PostMapping
    public Mono<User> createUser(@Valid @RequestBody User user) {
        return userService.createUser(user).doFirst(() -> log.info("Create User request received for user data : {}", user));
    }
}
