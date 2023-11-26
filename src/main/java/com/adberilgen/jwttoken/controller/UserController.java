package com.adberilgen.jwttoken.controller;

import com.adberilgen.jwttoken.dto.AuthRequest;
import com.adberilgen.jwttoken.dto.CreateUserRequest;
import com.adberilgen.jwttoken.model.User;
import com.adberilgen.jwttoken.service.JwtService;
import com.adberilgen.jwttoken.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello user!";
    }


    @PostMapping("/addNewUser")
    public User addNewUser(@RequestBody CreateUserRequest createRequestBody) {
        return userService.createUser(createRequestBody);
    }

    @PostMapping("/generateToken")
    public String geneateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.username(), authRequest.password()
                ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.username());
        }
        log.info("invalid username" + authRequest.username());
        throw new UsernameNotFoundException("invalid username {} " + authRequest.username());
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is user";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is admin";
    }
}