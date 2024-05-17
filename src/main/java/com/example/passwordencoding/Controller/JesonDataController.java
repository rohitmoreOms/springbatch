package com.example.passwordencoding.Controller;

import com.example.passwordencoding.service.IRootClassService;
import com.example.passwordencoding.service.IjesonServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JesonDataController {

    @Autowired
    private IjesonServise ijesonServise;

    @Autowired
    private IRootClassService iRootClassService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?>saveOrUpdate() {
        try {
            return new ResponseEntity(ijesonServise.saveOrUpdate(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("/saveData")
    public ResponseEntity<?>saveData() {
        try {
            return new ResponseEntity(iRootClassService.saveData(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }


    @PostMapping("/saveDataEntity")
    public ResponseEntity<?>saveDataEntity() {
        try {
            return new ResponseEntity(iRootClassService.saveDataEntity(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
