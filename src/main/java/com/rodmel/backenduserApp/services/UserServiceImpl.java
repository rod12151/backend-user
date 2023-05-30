package com.rodmel.backenduserApp.services;

import com.rodmel.backenduserApp.models.entities.Role;
import com.rodmel.backenduserApp.models.entities.User;
import com.rodmel.backenduserApp.repositories.RoleRepository;
import com.rodmel.backenduserApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> o = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (o.isPresent()){
            roles.add(o.orElseThrow());
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(User user, Long id) {
        Optional<User> o = this.findById(id);
        User userOptional =null;
        if (o.isPresent()){
            User userDb = o.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());
            userOptional = this.save(userDb);
        }

        return Optional.ofNullable(userOptional);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);

    }
}
