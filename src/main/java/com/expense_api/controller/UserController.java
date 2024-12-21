package com.expense_api.controller;

import com.expense_api.payload.UserRegistrationRequest;
import com.expense_api.payload.UserRegistrationResponse;
import com.expense_api.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        // Log the incoming registration request
        logger.info("Received registration request for user: {}", userRegistrationRequest.getUsername());

        // Register user via service
        UserRegistrationResponse userRegistrationResponse = userService.registerUser(userRegistrationRequest);

        // Log success
        logger.info("User {} registered successfully.", userRegistrationRequest.getUsername());

        // Return successful response
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.CREATED);
    }
}
