package com.rodmel.backenduserApp.controllers;

import com.rodmel.backenduserApp.models.entities.User;
import com.rodmel.backenduserApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> finById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id).orElseThrow());

    }
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){
        Optional<User> o= userService.update(user,id);
        if(o.isPresent()){
           return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> o = userService.findById(id);
        if(o.isPresent()){
            userService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
