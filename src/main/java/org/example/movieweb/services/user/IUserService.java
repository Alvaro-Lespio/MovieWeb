package org.example.movieweb.services.user;

import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.models.User;

import java.util.List;

public interface IUserService {
    String createUsers(User user);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(User user);
}
