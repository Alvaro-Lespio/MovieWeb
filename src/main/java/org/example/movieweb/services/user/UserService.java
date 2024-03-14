package org.example.movieweb.services.user;

import org.example.movieweb.DTO.MovieNameDTO;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.DTO.UserSimplifiedlDTO;
import org.example.movieweb.exceptions.IdNotFound;
import org.example.movieweb.exceptions.MovieNameNotFound;
import org.example.movieweb.exceptions.UserCreateFaild;
import org.example.movieweb.exceptions.UserUpdateFailed;
import org.example.movieweb.models.Movie;
import org.example.movieweb.models.User;
import org.example.movieweb.repositories.IMovieRepository;
import org.example.movieweb.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    //Inyeccion de dependencia del repository
    IUserRepository userRepository;
    IMovieRepository movieRepository;
    public UserService(IUserRepository userRepository, IMovieRepository movieRepository){
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    //Crear el usuario
    @Override
    //@Transactional lo que hace es que si ocurre una excepcion el pasaje de datos se revierte automaticamente
    public String createUsers(User user) {
        if(user == null){
            throw new UserCreateFaild("El usuario es incorrecto verifique los campos");
        }else if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){
            throw new UserCreateFaild("El usuario es incorrecto verifique alguno de sus campos");
        }else if(user.getUserName().isBlank() || user.getName().isBlank() || user.getEmail().isBlank() || user.getLastName().isBlank() || user.getPassword().isBlank()){
            throw new UserCreateFaild("los datos del usuario estan incompletos");
        }else {
            userRepository.save(user);
            return "El usuario se creo correctamente";
        }
    }
    //listar los usuarios
    @Override
    public List<UserSimplifiedlDTO> getAllUsers() {
        List<User> users = userRepository.getAll();
        List<UserSimplifiedlDTO> userSimplifiedlDTOS = users.stream().map(user-> new UserSimplifiedlDTO(user.getUserName(),user.getPassword())).collect(Collectors.toList());
        return userSimplifiedlDTOS;
    }

    //actualizar los usuarios
    @Override
    public String updateUser(UserDTO user, Long id) {

        if(user == null){
            throw new UserUpdateFailed("No se encuentra ningun dato");
        }else if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){
            throw new UserUpdateFailed("Verifique sus datos, son incorrectos");
        }else if(user.getUserName().isBlank() || user.getName().isBlank() || user.getEmail().isBlank() || user.getLastName().isBlank() || user.getPassword().isBlank()){
            throw new UserUpdateFailed("Verifique sus datos, son incompletos");
        }
            String message = userRepository.findById(id).map(us ->{
                us.setUserName(user.getUserName());
                us.setName(user.getName());
                us.setEmail(user.getEmail());
                us.setPassword(user.getPassword());
                us.setLastName(user.getLastName());
                userRepository.save(us);
                return "El usuario se actualizo correctamente";
            }).orElse("El id "+ id + "no existe");

            return message;

    }
    //Eliminar un usuario por id
    @Override
    public String deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            return "El Usuario se elimino correctamente";
        }else {
            return "El id "+ id + " no existe";
        }
    }
    //añadir desde un usuario a la lista de peliculas
    @Override
    public String addToListMovie(Long userID, String movieID) {

        User user = userRepository.findById(userID).orElseThrow(() -> new IdNotFound("El id del usuario no se encuentra verifique nuevamente"));
        Movie movie = movieRepository.findById(movieID).orElseThrow(()-> new MovieNameNotFound("El id de la pelicula no se encuentra verifique nuevamente"));

        Optional<Movie> movie1 = user.getMovies().stream().filter(mov -> mov.getSeries_Title().equals(movie.getSeries_Title())).findFirst();
        if(movie1.isPresent()){
            return "La pelicula ya esta añadida a la biblioteca";
        }else{
            user.getMovies().add(movie);
            userRepository.save(user);
            MovieNameDTO movieNameDTO = new MovieNameDTO(movie.getSeries_Title());
            String message = "La pelicula " + movieNameDTO.getMovieName() + "fue añadida exitosamente!";
            return message;
        }
    }

    @Override
    public Set<MovieNameDTO> movieList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IdNotFound("El id del usuario no se encuentra"));
        Set<Movie> movieName = user.getMovies();

        Set<MovieNameDTO> movieNameDTOS = movieName.stream().map(mov ->{
             return new MovieNameDTO(mov.getSeries_Title());
        }).collect(Collectors.toSet());

        return movieNameDTOS;
    }


}
