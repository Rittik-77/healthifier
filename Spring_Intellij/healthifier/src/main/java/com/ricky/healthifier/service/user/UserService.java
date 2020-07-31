package com.ricky.healthifier.service.user;

import com.ricky.healthifier.utils.exception.AppException;

public interface UserService {

    String extractUsernameFromEmail(String email) throws AppException;
}
