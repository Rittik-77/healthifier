package com.ricky.healthifier.controller.auth;

import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.service.auth.AuthService;
import com.ricky.healthifier.utils.commons.BaseConstants;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(BaseConstants.APP_LINK)
public class AuthController {

    @Autowired
    private AuthService authService;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserVOTransformer transformer = new UserVOTransformer();

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean register(@RequestBody UserVO userVO) throws AppException, NoSuchAlgorithmException {

        logger.info("Rest: User Registration");
        BaseValidator.checkObjectIsNotNull(userVO, "UserVO payload should not be null");
        User user = transformer.transformToModel(userVO);
        return authService.register(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String login(@RequestBody UserVO userVO) throws NoSuchAlgorithmException, AppException {

        logger.info("Rest: User Logging");
        BaseValidator.checkObjectIsNotNull(userVO, "UserVO payload should not be null");
        User user = transformer.transformToModel(userVO);
        return authService.login(user);
    }
}
