package com.piolin.spring.prototype.controller;

import com.piolin.spring.prototype.config.PropsConfig;
import com.piolin.spring.prototype.database.entity.AuthRequest;
import com.piolin.spring.prototype.pojo.Root;
import com.piolin.spring.prototype.security.service.JwtService;
import com.piolin.spring.prototype.util.Cons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    PropsConfig propsConfig;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;

    @GetMapping(path = "/root", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Root> index(){
        Root root = new Root(Cons.ROOT_ENDPOINT_MSG, propsConfig.getPropsValue(Cons.APP_VERSION));
        return new ResponseEntity<>(root, HttpStatus.OK);
    }

    @PostMapping("/get-token")
    public String authEAndGetToken(@RequestBody AuthRequest authRequest){
        LOG.info("Getting a new JWT token ...");
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPwd()));

        LOG.info("Authentication via Auth Manager is authenticated: {}", auth.isAuthenticated());
        LOG.info("Authentication via Auth Manager Details: {}", auth.getDetails());

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid User ...");
        }
    }

}