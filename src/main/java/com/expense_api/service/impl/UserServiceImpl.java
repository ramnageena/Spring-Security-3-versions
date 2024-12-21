package com.expense_api.service.impl;

import com.expense_api.entity.User;
import com.expense_api.payload.UserRegistrationRequest;
import com.expense_api.payload.UserRegistrationResponse;
import com.expense_api.repository.UserRepository;
import com.expense_api.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        // Log the incoming registration request
        logger.info("Received registration request for user: {}", userRegistrationRequest.getUsername());

        try {
            // Map the UserRegistrationRequest to User entity
            User user = modelMapper.map(userRegistrationRequest, User.class);

            // Encode the password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Save the user to the repository
            User savedUser = userRepository.save(user);

            // Log the successful registration
            logger.info("User {} registered successfully.", savedUser.getUsername());

            // Return the response DTO
            return modelMapper.map(savedUser, UserRegistrationResponse.class);
        } catch (Exception e) {
            // Log any exception that occurs
            logger.error("Error occurred while registering user: {}", userRegistrationRequest.getUsername(), e);
            throw new RuntimeException("User registration failed");
        }
    }
}
