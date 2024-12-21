package com.expense_api.service;

import com.expense_api.entity.User;
import com.expense_api.payload.UserRegistrationRequest;
import com.expense_api.payload.UserRegistrationResponse;

public interface UserService {
    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
}
