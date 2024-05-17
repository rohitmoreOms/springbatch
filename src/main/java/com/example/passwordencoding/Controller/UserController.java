package com.example.passwordencoding.Controller;

import com.example.passwordencoding.model.request.UserRequest;
import com.example.passwordencoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
  private  IUserService iUserService;

    @PostMapping("/saveOrUpdateUser")
    public ResponseEntity<?> saveOrUpdateUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        try {
            return new ResponseEntity(iUserService.saveOrUpdateUser(userRequest,bindingResult), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("getAllUser")
    public ResponseEntity<?> getAllUser(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
                                           ) {
        try {
            Pageable pageable= PageRequest.of(Optional.ofNullable(pageNo).orElse(0),Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(iUserService.getAllUser(pageable ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteByUserId")
    public ResponseEntity<?>deleteByUserId(@RequestParam Long userId) {
        try {
            return new ResponseEntity(iUserService.deleteByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<?>getByUserId(@RequestParam Long userId) {
        try {
            return new ResponseEntity(iUserService.getByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
