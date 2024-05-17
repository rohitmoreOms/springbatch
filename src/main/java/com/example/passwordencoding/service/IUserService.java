package com.example.passwordencoding.service;

import com.example.passwordencoding.model.request.UserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

public interface IUserService {
    Object saveOrUpdateUser(UserRequest userRequest, BindingResult bindingResult);

    Object getAllUser(Pageable pageable);

    Object deleteByUserId(Long userId);

    Object getByUserId(Long userId);
}
