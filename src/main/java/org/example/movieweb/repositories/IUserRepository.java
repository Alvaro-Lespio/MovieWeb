package org.example.movieweb.repositories;

import org.example.movieweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    @Query("FROM User")
    List<User> getAll();


}
