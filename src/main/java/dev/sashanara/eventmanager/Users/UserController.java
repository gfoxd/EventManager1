package dev.sashanara.eventmanager.Users;

import dev.sashanara.eventmanager.Security.JwtUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger log =  LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private UserConverter userConverter;
    private JwtUtil jwtUtil;

    public UserController(
            UserService userService,
            UserConverter userConverter,
            JwtUtil jwtUtil
    ) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser (
            @Valid
            @RequestBody
            UserDto userDto
    ) {
        log.info("UserController request registerUser");

        User user = userService.registerUser(userConverter.toDomain(userDto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userConverter.toDto(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById (
            @PathVariable Long userId
    ) {
        log.info("UserController request getUserById");

        UserDto userDto =  userConverter.toDto(
                userService.getUserById(userId)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authUser (
            @RequestBody UserDto userDto
    ) {
        log.info("UserController request authUser");

        User authenticatedUser = userService.authenticate(
                userDto.login(),
                userDto.password()
        );

        String token = jwtUtil.generateToken(
                authenticatedUser.login(),
                authenticatedUser.role()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);
    }

}
