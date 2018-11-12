package com.vvm.booking.repository;

import com.vvm.booking.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
