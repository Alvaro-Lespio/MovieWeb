package org.example.movieweb.services.user;

import jakarta.transaction.Transactional;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.exceptions.UserCreateFaild;
import org.example.movieweb.exceptions.UserUpdateFailed;
import org.example.movieweb.models.User;
import org.example.movieweb.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    //Inyeccion de dependencia del repository
    IUserRepository userRepository;
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Crear el usuario
    @Override
    //@Transactional lo que hace es que si ocurre una excepcion el pasaje de datos se revierte automaticamente
    public String createUsers(User user) {
        if(user == null){
            throw new UserCreateFaild("El usuario es incorrecto verifique los campos");
        }else if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){
            throw new UserCreateFaild("El usuario es incorrecto verifique alguno de sus campos");
        }else{
            userRepository.save(user);
            return "El usuario se creo correctamente";
        }
    }
    //listar los usuarios
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.getAll();
        List<UserDTO> userDTOS = users.stream().map(user-> new UserDTO(user.getUserName(),user.getPassword())).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public UserDTO updateUser(User user) {
        if(user == null){
            throw new UserUpdateFailed("No se encuentra ningun dato");
        }else if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){
            throw new UserUpdateFailed("Verifique sus datos, son incorrectos");
        }else{

        }

        return null;
    }

    //actualizar los usuarios

}
