package com.ricky.healthifier.service.auth;

import com.ricky.healthifier.datamodel.user.User;

import java.util.Date;

public interface JwtService {

    String extractUsername(String token);

    Date extractExpirationDate(String token);

    String generateToken(User user);

    boolean validateToken(String token, User user);
}
