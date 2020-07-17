package com.ricky.healthifier.controller.auth;

import com.ricky.healthifier.datamodel.user.RoleEnum;
import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.utils.exception.AppException;

public class UserVOTransformer {

    private static final String INVALID_ROLE = "Invalid value found for Role";

    public User transformToModel(UserVO userVO) throws AppException {
        User user = new User();
        if (userVO.getEmail() != null)
            user.setEmail(userVO.getEmail());
        if (userVO.getUsername() != null)
            user.setUsername(userVO.getUsername());
        if (userVO.getPassword() != null)
            user.setPassword(userVO.getPassword());
        if (userVO.getRole() != null)
            user.setRoleEnum(convertToEnum(userVO.getRole()));
        if (userVO.getWeight() != null)
            user.setWeight(userVO.getWeight());
        return user;
    }

    private RoleEnum convertToEnum(String role) throws AppException {
        if (role.equals("ADMIN"))
            return RoleEnum.ADMIN;
        else if (role.equals("USER"))
            return RoleEnum.USER;

        throw new AppException(INVALID_ROLE);
    }
}
