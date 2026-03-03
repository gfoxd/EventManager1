package dev.sashanara.eventmanager.Users;

import dev.sashanara.eventmanager.Exeptions.AgeValidationException;
import dev.sashanara.eventmanager.Exeptions.LoginAlreadyExistsException;
import dev.sashanara.eventmanager.Locations.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

import static dev.sashanara.eventmanager.Users.Role.USER;

@Service
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserConverter userConverter,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User getUserById(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        return userConverter.toDomain(
                userRepository.findById(userId)
                .orElseThrow(() ->
                        new ExpressionException("User not found with id: " + userId)));
    }

    @Transactional
    public User registerUser(User user) {

        if (!(userRepository.findByLogin(user.login()) == null)){
            throw new LoginAlreadyExistsException("Login already exists");
        }

        if (user.age() < 18) {
            throw new AgeValidationException("Age must be over 18");
        }

        String hashedPassword = passwordEncoder.encode(user.password());

        UserEntity userEntity = userConverter.toEntity(user);
        userEntity.setPasswordHash(hashedPassword);
        userEntity.setRole(USER);

        UserEntity savedUser = userRepository.save(userEntity);

        return userConverter.toDomain(savedUser);
    }

    public User authenticate(String login, String password) {
        UserEntity userEntity = userRepository.findByLogin(login);

        if (userEntity == null) {
            throw new RuntimeException("User not found with login: " + login);
        }

        if (!passwordEncoder.matches(password, userEntity.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return userConverter.toDomain(userEntity);
    }

}
