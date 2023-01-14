package com.speclogistics.user.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speclogistics.user.service.entity.User;
import com.speclogistics.user.service.models.dto.UserDto;
import com.speclogistics.user.service.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final ObjectMapper mapper;

    public Flux<User> getAll(){
        return userRepository.findAll().switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"No Data found")))
                .onErrorResume(error -> {
                    if(error instanceof ResponseStatusException){
                        throw (ResponseStatusException)error;
                    }
                    log.error("Error occurred while fetching data from database", error);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid request, please check the data provided");
                });
    }

    public Mono<User> createUser(UserDto userDto){
        User user = mapper.convertValue(userDto,User.class);
        if(userDto.getParentId() != -1){
            user.setDateOfAssociation(LocalDate.now());
        }
        return userRepository.save(user).onErrorResume(error -> {
            log.error("Error occurred while storing user in database", error);
            if(error instanceof DataIntegrityViolationException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,((DataIntegrityViolationException) error).getRootCause().getMessage());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid request, please check the data provided");
        });
    }
}
