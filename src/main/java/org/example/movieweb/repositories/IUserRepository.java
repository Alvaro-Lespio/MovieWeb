package org.example.movieweb.repositories;

import org.example.movieweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    //Creamos mediante HQL una consulta personaliazada para que desde la base de datos nos traiga todos los usuarios
    @Query("FROM User")
    List<User> getAll();


}
