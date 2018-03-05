package com.springangular.controller;

import com.springangular.exceptions.ApiResponse;
import com.springangular.modal.User;
import com.springangular.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.SOAPPart;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private List<User> EmptyList = new ArrayList<User>();

    @Autowired
    UserRepository userRepository;

    //Create user
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    //Get User
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> get(@PathVariable String id) {
        if (id.equalsIgnoreCase("all")) {
            List<User> userList = userRepository.findAll();
            if (userList.size() > 0) {
                return new ApiResponse(userList).send(HttpStatus.OK, "Users found");
            } else {
                return new ApiResponse(EmptyList).send(HttpStatus.NOT_FOUND, "Users not found");
            }
        } else {
            User list = userRepository.findOne(id);
            if (list == null) {
                return new ApiResponse(EmptyList).send(HttpStatus.NOT_FOUND, "User not found");
            } else {
                return new ApiResponse(list).send(HttpStatus.OK, "User found");
            }
        }
    }

    //Update User
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse> update(@RequestBody User user) {
        userRepository.save(user);
        return new ApiResponse(EmptyList).send(HttpStatus.NO_CONTENT, "Update success");
    }

    //Delete User
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        userRepository.delete(id);
        return new ApiResponse(EmptyList).send(HttpStatus.ACCEPTED, "Item deleted");
    }
}