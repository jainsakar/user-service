package com.speclogistics.user.service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.speclogistics.order.service.grpcservice.PongResponse;
import com.speclogistics.user.service.entity.User;
import com.speclogistics.user.service.models.dto.UserDto;
import com.speclogistics.user.service.service.GrpcClientService;
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

    private final GrpcClientService grpcClientService;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping
    public Flux<User> getUsers() {
        return userService.getAll().doFirst(() -> log.info("Get All users request received"))
                .collectList().flatMapMany(userList -> {
                    log.info("found {} users on get users request", userList.size());
                    return Flux.fromIterable(userList);
                });
    }

    @GetMapping (value = "/ping")
    public Mono<PongResponse> getPing() {
        return grpcClientService.ping();
    }


    @PostMapping
    public Mono<User> createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto).doFirst(() -> log.info("Create User request received for user data : {}", userDto));
    }

    @PatchMapping ({"/{userId}"})
    public Mono<User> updateUser(@Valid @RequestBody UserDto userPatchDto, @PathVariable int userId){
        return userService.updateUser(userPatchDto,userId);
    }

    @DeleteMapping ({"/{userId}"})
    public Mono<User> enableDisableUser(@PathVariable int userId, @RequestParam (name = "active") final boolean active){
        log.info("enable/Disable Request received for user id : {}", userId);
        return userService.enableDisableUser(userId,active);
    }
}
