package com.ricky.healthifier.entity.user;

import com.ricky.healthifier.datamodel.user.RoleEnum;
import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.utils.exception.AppException;

public class UserDTOTransformer {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private static final String INVALID_ROLE_IN_DB = "Invalid value for Role Enum Found in Database";

    public User transformToModel(UserDTO userDTO) throws AppException {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setWeight(userDTO.getWeight());
        user.setRoleEnum(stringToEnum(userDTO.getRoleEnumDTO()));
        return user;
    }

    private RoleEnum stringToEnum(RoleEnumDTO roleEnumDTO) throws AppException {
        if(roleEnumDTO.getRole().equals(ADMIN))
            return RoleEnum.ADMIN;
        else if(roleEnumDTO.getRole().equals(USER))
            return RoleEnum.USER;

        throw new AppException(INVALID_ROLE_IN_DB);
    }

    public UserDTO transformToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if(user.getId() != null)
            userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setWeight(user.getWeight());
        userDTO.setRoleEnumDTO(new RoleEnumDTO(user.getRoleEnum().toString()));
        return userDTO;
    }
}
