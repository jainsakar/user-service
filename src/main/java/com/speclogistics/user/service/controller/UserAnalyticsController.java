package com.speclogistics.user.service.controller;

import com.speclogistics.user.service.entity.User;
import com.speclogistics.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users/analytics")
@Slf4j
public class UserAnalyticsController {

    private final UserService userService;

    @GetMapping(value = "/clients")
    public Mono<List<User>> getAllClientsDetailsForUser(@RequestParam(name = "parentId") int parentId) {
        return userService.getAllClientsDetailsForUser(parentId).collectList().flatMap(clientList -> {
            log.info("Received {} client info for the parent id: {}", clientList.size(), parentId);
            return Mono.just(clientList);
        });
    }
}
