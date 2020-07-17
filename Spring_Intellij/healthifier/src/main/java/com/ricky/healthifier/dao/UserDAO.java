package com.ricky.healthifier.dao;

import com.ricky.healthifier.entity.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserDTO, String> {

}
