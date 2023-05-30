package com.rodmel.backenduserApp.repositories;

import com.rodmel.backenduserApp.models.entities.Role;
import com.rodmel.backenduserApp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String username);

}
