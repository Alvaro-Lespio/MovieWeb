package org.example.movieweb.services.user;

import org.example.movieweb.DTO.MovieNameDTO;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.DTO.UserLoginDto;
import org.example.movieweb.exceptions.IdNotFoundException;
import org.example.movieweb.exceptions.MovieNameNotFoundException;
import org.example.movieweb.exceptions.UserCreateFaildException;
import org.example.movieweb.exceptions.UserUpdateFailedException;
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

    //Inyeccion de dependencia del repository y del repository de movie
    IUserRepository userRepository;

    IMovieRepository movieRepository;
    public UserService(IUserRepository userRepository, IMovieRepository movieRepository){
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    //Crear el usuario
    @Override
    public String createUsers(UserDTO user) {


        String mensaje = "El usuario se creo correctamente";
        User u = new User();
        //verificamos que el usuario que nos llega por parametro no sea nullo, en caso de que lo sea intentamos lanzar una nueva
        //Excepcion personalizada
        if(user == null){
            throw new UserCreateFaildException("El usuario es nulo");
        }else if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){

            //Verificamos que ninguno de los atributos del objeto sean nulos, en caso de que lo sea alguno, lanzamos una excepcion
            //personalizada
            throw new UserCreateFaildException("El usuario es incorrecto verifique alguno de sus campos");
        }else if(user.getUserName().isBlank() || user.getName().isBlank() || user.getEmail().isBlank() || user.getLastName().isBlank() || user.getPassword().isBlank()){

            //Verificamos que nignuno de los atributos del objeto esten en blanco, en caso de que contenga algun atributo en blanco
            //se lanza una excepcion personalizada
            throw new UserCreateFaildException("los datos del usuario estan incompletos");
        }else {

            //en el caso de que este bien, se guarda en la base de datos, mediante el metodo save de JPA repository
            //y le pasamos el objeto, y retornamos un mensaje de que salio correctamente

                u.setUserName(user.getUserName());
                u.setPassword(user.getPassword());
                u.setEmail(user.getEmail());
                u.setLastName(user.getLastName());
                u.setName(user.getName());
                userRepository.save(u);

            return mensaje;
        }
    }
    //listar los usuarios
    @Override
    public List<UserLoginDto> getAllUsers() {
        //Accedemos a todos los usuarios de nuestra base de datos mediante el metodo getAll,que en este caso es una consulta HQL
        //desde el repositorio de usuario y los guardamos dentro de una lista, a esa lista la recorremos y convertimos los datos
        //En DTO, ya que el dto de usuario contiene solamente el usuario y la contraseña. A este mismo lo almacenamos
        //dentro de una lista dto, y retornamos esa lista creada.
        List<User> users = userRepository.getAll();
        List<UserLoginDto> userLoginDtos = users.stream().map(user-> new UserLoginDto(user.getUserName(),user.getPassword())).collect(Collectors.toList());
        return userLoginDtos;
    }

    //actualizar los usuarios
    @Override
    public String updateUser(UserDTO user, Long id) {
        //Verificamos que ninguno de los atributos del objeto sean nulos, en caso de que lo sea alguno, lanzamos una excepcion
        //personalizada
        if(user == null){
            throw new UserUpdateFailedException("No se encuentra ningun dato");
        }else if(user.getUserName() == null || user.getName() == null || user.getEmail() == null || user.getLastName() == null || user.getPassword() == null){
            //Verificamos que ninguno de los atributos del objeto sean nulos, en caso de que lo sea alguno, lanzamos una excepcion
            //personalizada
            throw new UserUpdateFailedException("Verifique sus datos, son incorrectos");
        }else if(user.getUserName().isBlank() || user.getName().isBlank() || user.getEmail().isBlank() || user.getLastName().isBlank() || user.getPassword().isBlank()){
            //Verificamos que nignuno de los atributos del objeto esten en blanco, en caso de que contenga algun atributo en blanco
            //se lanza una excepcion personalizada
            throw new UserUpdateFailedException("Verifique sus datos, son incompletos");
        }
            //En caso de que este bien el usuario, vamos a buscar al usuario mediante su id
            //luego vamos a utilizar buscar por id del propio jpa y vamos a settear o actualizar
            //los valores del usuario anterior. vamos a retornar un valor que se va a guardar en una variable
            //y se va a retornar esa variable
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
        //En este caso creamos un user del tipo Optional para poder buscar por id, y poder acceder al metodo si esta presente
        //En caso de que lo este, se elimina el usuario mediante el metodo eliminar por id del repositorio y se va a retronar
        //un mensaje para saber si la eliminacion resulto correcta o no.
        String message = "El id "+ id + " no existe";
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            message = "El Usuario se elimino correctamente";
        }
            return message;

    }
    //añadir desde un usuario a la lista de peliculas
    @Override
    public String addToListMovie(Long userID, String movieName) {
        //En este caso cada usuario tiene su lista de peliculas, primero buscamos un usuario por id, mediante el metodo
        // buscar por id del usuario propio de jpa, y el valor que retorne lo guardamos en una variable del tipo user,
        //luego hacemos lo mismo con movie,pero con su repositorio, y lo que retorne lo va a almacenar en una variable
        //de tipo movie.En caso de que no se encuentre el nombre se intenta lanzar un error personalziado.

        String message;

        System.out.println(movieName);
        User user = userRepository.findById(userID).orElseThrow(() -> new IdNotFoundException("El id del usuario no se encuentra verifique nuevamente"));
        //Movie movie = movieRepository.findByTitle(movieName).stream().findFirst().orElseThrow(() -> new MovieNameNotFound("El título de la película no se encuentra"));
        Movie movie = movieRepository.findByTitle(movieName).stream().findFirst().orElseThrow(() -> new MovieNameNotFoundException("El titulo de la pelicula no existe"));

            user.getMovies().add(movie);
            userRepository.save(user);
            message = "La pelicula " + movieName + " fue añadida exitosamente!";
            return message;

    }

    //Mostrar la biblioteca de peliculas
    @Override
    public Set<MovieNameDTO> movieList(Long userId) {
        //Buscamos el usuario por id mediante el metodo busacar por id propio de jpa, en caso de que no este ese id
        //mostramos un error personalizado y en caso de que lo encuentre lo guardamos dentro de un Set de movie, y le
        //asignamos la biblioteca del usuario
        User user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException("El id del usuario no se encuentra"));
        Set<Movie> movieName = user.getMovies();

        //Convertimos set movie a un set de movie dto el cual solo tiene como atributo el nombre de la pelicula y lo
        //guardamos en el set nuevamente.
        Set<MovieNameDTO> movieNameDTOS = movieName.stream().map(mov ->{
              return new MovieNameDTO(mov.getSeries_Title());
        }).collect(Collectors.toSet());

        return movieNameDTOS;
    }


}
