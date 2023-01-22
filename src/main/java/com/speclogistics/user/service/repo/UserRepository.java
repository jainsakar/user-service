package com.speclogistics.user.service.repo;

import com.speclogistics.user.service.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
     Flux<User> findAllByParentId(int parentId);
}
