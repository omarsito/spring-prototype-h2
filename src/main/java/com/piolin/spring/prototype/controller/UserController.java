package com.piolin.spring.prototype.controller;

import com.piolin.spring.prototype.database.entity.UserInfo;
import com.piolin.spring.prototype.security.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//ready for use by Spring MVC to handle web requests
//Combines @Controller & @ResponseBody
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserInfoService service;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> insertClient(@RequestBody UserInfo userInfo){
        LOG.info("Creating new user ... data: {}", userInfo.toString());
        return new ResponseEntity<>(service.addUser(userInfo), HttpStatus.CREATED);
    }

}