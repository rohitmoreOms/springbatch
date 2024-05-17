package com.example.passwordencoding.service.impl;

import com.example.passwordencoding.entity.User;
import com.example.passwordencoding.model.request.UserRequest;
import com.example.passwordencoding.repository.UserRepository;
import com.example.passwordencoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Object saveOrUpdateUser(UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (userRepository.existsById(userRequest.getUserId())) {
                User user = userRepository.findById(userRequest.getUserId()).get();
                user.setAbout(userRequest.getAbout());
                user.setEmail(userRequest.getEmail());
                user.setName(userRequest.getName());
                userRepository.save(user);
                return "updated";

            } else {
                User user = new User();
                user.setAbout(userRequest.getAbout());
                user.setEmail(userRequest.getEmail());
                user.setName(userRequest.getName());
                userRepository.save(user);
                return "saved";
            }
        }  Map<String, String> validationErrors = new HashMap<>();
        // Iterate over field errors and populate the map
        for (FieldError error : bindingResult.getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }
        // Return the map of validation errors
        return ResponseEntity.badRequest().body(validationErrors);
    }

    @Override
    public Object getAllUser(Pageable pageable) {
        Page<User>page =userRepository.findAll(pageable);
        return page;
    }

    @Override
    public Object deleteByUserId(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "deleted";
        } else {
            return "not exist";
        }
    }

    @Override
    public Object getByUserId(Long userId) {
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).get();
            return user;
        }else {
            return "not exist";
        }
    }
}
