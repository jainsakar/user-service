package com.speclogistics.user.service.service;

import com.speclogistics.user.service.entity.User;
import com.speclogistics.user.service.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }
}
