package com.rodmel.backenduserApp.repositories;

import com.rodmel.backenduserApp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

/*    @Query("select u from User  u where u.username=?1")
    Optional<User> getUserByUsername(String username);*/
}
