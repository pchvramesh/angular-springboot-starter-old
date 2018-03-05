package com.springangular.repository;

import com.springangular.modal.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    public User findByName(String name);


}
