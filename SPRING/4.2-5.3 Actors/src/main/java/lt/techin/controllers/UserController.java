package lt.techin.controllers;

import jakarta.validation.Valid;
import lt.techin.dto.UserDTO;
import lt.techin.dto.UserMapper;
import lt.techin.model.User;
import lt.techin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){return ResponseEntity.ok(UserMapper.toUserDTOList(userService.findAllUsers()));}

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are already registered!");
        }

        if (userService.existsUserByUsername(userDTO.username())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this username already exists!");
        }
        User user = UserMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userService.saveUser(user);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri())
                .body(UserMapper.toUserDTO(savedUser));
    }
}
