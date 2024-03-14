package org.example.movieweb.services.user;

import org.example.movieweb.DTO.MovieNameDTO;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.DTO.UserSimplifiedlDTO;
import org.example.movieweb.models.User;

import java.util.List;
import java.util.Set;

public interface IUserService {
    String createUsers(User user);
    List<UserSimplifiedlDTO> getAllUsers();
    String updateUser(UserDTO userDTO, Long id);
    String deleteUser(Long id);

    String addToListMovie(Long userID, String movieID);

    Set<MovieNameDTO> movieList(Long id);
}
