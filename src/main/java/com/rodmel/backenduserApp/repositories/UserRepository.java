package com.rodmel.backenduserApp.repositories;

import com.rodmel.backenduserApp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
