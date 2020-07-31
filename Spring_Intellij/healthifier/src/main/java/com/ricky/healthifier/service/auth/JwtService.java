package com.ricky.healthifier.service.auth;

import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.utils.exception.AppException;

public interface JwtService {

    String extractEmail(String token);

    String generateToken(User user);

    void checkIsTokenExpired(String token) throws AppException;
}
