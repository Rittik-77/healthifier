package com.ricky.healthifier.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.service.auth.AuthService;
import com.ricky.healthifier.service.auth.JwtService;
import com.ricky.healthifier.service.user.UserService;
import com.ricky.healthifier.utils.commons.BaseConstants;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(BaseConstants.APP_LINK)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserVOTransformer transformer = new UserVOTransformer();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean register(@RequestBody UserVO userVO) throws AppException, NoSuchAlgorithmException {

        logger.info("Rest: User Registration");
        BaseValidator.checkObjectIsNotNull(userVO, "UserVO payload should not be null");
        User user = transformer.transformToModel(userVO);
        return authService.register(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserVO userVO) throws NoSuchAlgorithmException, AppException, JsonProcessingException {

        logger.info("Rest: User Logging");
        BaseValidator.checkObjectIsNotNull(userVO, "UserVO payload should not be null");
        User user = transformer.transformToModel(userVO);
        String token = authService.login(user);
        return objectMapper.writeValueAsString(token);
    }

    @RequestMapping(value="loggedUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUsernameForLoggedUser(@RequestHeader Map<String, String> headers) throws AppException, JsonProcessingException {

        logger.info("Rest: Fetching Username for logged in user");
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);
        String email = jwtService.extractEmail(token);
        String username = userService.extractUsernameFromEmail(email);
        return objectMapper.writeValueAsString(username);
    }
}
