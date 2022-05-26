package com.aaa.backend.Repositories;

import java.util.Optional;

import com.aaa.backend.Models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    public abstract Optional<User> findByEmail(String email);
}
