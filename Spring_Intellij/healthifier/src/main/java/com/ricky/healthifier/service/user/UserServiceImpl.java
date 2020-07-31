package com.ricky.healthifier.service.user;

import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String extractUsernameFromEmail(String email) throws AppException {

        logger.info("Service: Start fetching username from email");

        // Validate if email is not null
        BaseValidator.checkObjectIsNotNull(email, "Email should not be null");

        // Validate if user with this email exists
        UserDTO userDTO = userDAO.findById(email).orElse(null);
        if(userDTO == null)
            throw new AppException("User with given email id does not exist");

        logger.info("Success: Fetching username from email");

        // Return username
        return userDTO.getUsername();
    }
}
