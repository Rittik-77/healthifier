package com.ricky.healthifier.service.auth;

import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.entity.user.UserDTOTransformer;
import com.ricky.healthifier.utils.commons.BaseConstants;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JwtService jwtService;

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserDTOTransformer transformer = new UserDTOTransformer();

    @Override
    public boolean register(User user) throws AppException, NoSuchAlgorithmException {

        logger.info(AuthServiceConstants.START_REGISTER);

        // Validate the payload
        BaseValidator.checkObjectIsNull(user.getId(), AuthServiceConstants.ID + BaseConstants.SPACE + BaseConstants.SHOULD_BE_NULL);
        BaseValidator.checkObjectIsNotNull(user.getEmail(), AuthServiceConstants.EMAIL + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);
        BaseValidator.checkObjectIsNotNull(user.getPassword(), AuthServiceConstants.PASSWORD + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);
        BaseValidator.checkObjectIsNotNull(user.getUsername(), AuthServiceConstants.USERNAME + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);
        BaseValidator.checkObjectIsNotNull(user.getRoleEnum(), AuthServiceConstants.ROLE + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);
        BaseValidator.checkObjectIsNotNull(user.getWeight(), AuthServiceConstants.WEIGHT + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);

        // Verify if User with same email exists
        Optional<UserDTO> userDTO = userDAO.findUserDTOByEmail(user.getEmail());
        BaseValidator.checkObjectIsNull(userDTO.orElse(null), AuthServiceConstants.USER_ALREADY_EXISTS);

        // Hash the password
        String pwd = user.getPassword();
        String hashedPwd = EncryptionUtil.encode(pwd);
        user.setPassword(hashedPwd);

        // Store to DB
        UserDTO newUserDTO = transformer.transformToDTO(user);
        userDAO.saveAndFlush(newUserDTO);

        logger.info(AuthServiceConstants.SUCCESS_REGISTER);
        return true;
    }

    @Override
    public String login(User loginUser) throws AppException, NoSuchAlgorithmException {

        logger.info(AuthServiceConstants.START_LOGIN);

        // Validate the Payload
        BaseValidator.checkObjectIsNull(loginUser.getId(), AuthServiceConstants.ID + BaseConstants.SPACE + BaseConstants.SHOULD_BE_NULL);
        BaseValidator.checkObjectIsNotNull(loginUser.getEmail(), AuthServiceConstants.EMAIL + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);
        BaseValidator.checkObjectIsNotNull(loginUser.getPassword(), AuthServiceConstants.PASSWORD + BaseConstants.SPACE + BaseConstants.SHOULD_NOT_BE_NULL);
        BaseValidator.checkObjectIsNull(loginUser.getUsername(), AuthServiceConstants.USERNAME + BaseConstants.SPACE + BaseConstants.SHOULD_BE_NULL);
        BaseValidator.checkObjectIsNull(loginUser.getRoleEnum(), AuthServiceConstants.ROLE + BaseConstants.SPACE + BaseConstants.SHOULD_BE_NULL);
        BaseValidator.checkObjectIsNull(loginUser.getWeight(), AuthServiceConstants.WEIGHT + BaseConstants.SPACE + BaseConstants.SHOULD_BE_NULL);

        // Extract the User from DataBase
        Optional<UserDTO> optionalUserDTO = userDAO.findUserDTOByEmail(loginUser.getEmail());
        UserDTO userDTO = optionalUserDTO.orElse(null);
        BaseValidator.checkObjectIsNotNull(userDTO, AuthServiceConstants.USER_DOES_NOT_EXISTS);

        // Validate user credentials
        String loginPassword = EncryptionUtil.encode(loginUser.getPassword());
        if(!loginPassword.equals(userDTO.getPassword()))
            throw new AppException(AuthServiceConstants.INVALID_PASSWORD);

        // Generate token
        User user = transformer.transformToModel(userDTO);
        String token = jwtService.generateToken(user);

        logger.info(AuthServiceConstants.SUCCESS_LOGIN);
        return token;
    }
}
