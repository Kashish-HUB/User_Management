package com.example.SpringProject.Controller;


import com.example.SpringProject.Model.User;
import com.example.SpringProject.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController // Marks this class as a REST controller, handling incoming HTTP requests
@RequestMapping("/api/users") // Base path for all endpoints in this controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role setup (ensure your User entity supports this)
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("ROLE_USER"));
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Retrieves all users from the database
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // @PathVariable extracts the ID from the URL path
        Optional<User> user = userRepository.findById(id); // Attempts to find user by ID
        return user.map(ResponseEntity::ok) // If user is present, return 200 OK with user
                .orElse(ResponseEntity.notFound().build()); // Otherwise, return 404 Not Found
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        // Find the existing user by ID
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Update the existing user's details
                    existingUser.setName(userDetails.getName());
                    existingUser.setEmail(userDetails.getEmail());
                    // Save the updated user back to the database
                    User updatedUser = userRepository.save(existingUser);
                    return ResponseEntity.ok(updatedUser); // Return 200 OK with the updated user
                })
                .orElse(ResponseEntity.notFound().build()); // If user not found, return 404 Not Found
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Check if the user exists before deleting
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id); // Delete the user
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // If user not found, return 404 Not Found
        }
    }

    @GetMapping("page")
    public Page<User> getUsers(Pageable pageable) {
        // Spring Data JPA's findAll method can accept a Pageable object
        // This automatically applies pagination and sorting based on query parameters
        return userRepository.findAll(pageable);
    }
}