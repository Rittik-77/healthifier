package com.ricky.healthifier.service.auth;

import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.utils.exception.AppException;

import java.security.NoSuchAlgorithmException;

public interface AuthService {

    boolean register(User user) throws AppException, NoSuchAlgorithmException;

    String login(User user) throws AppException, NoSuchAlgorithmException;
}
