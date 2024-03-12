package org.example.movieweb.services.user;

import org.example.movieweb.exceptions.UserCreateFaild;
import org.example.movieweb.models.User;
import org.example.movieweb.repositories.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    //Inyeccion de dependencia del repository
    IUserRepository userRepository;
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public String createUsers(User user) {
        if(user == null){
            throw new UserCreateFaild("El usuario es incorrecto verifique los campos");
        }
        if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){
            throw new UserCreateFaild("El usuario es incorrecto verifique alguno de sus campos");
        }
        userRepository.save(user);
        return "El usuario se creo correctamente";
    }
}
