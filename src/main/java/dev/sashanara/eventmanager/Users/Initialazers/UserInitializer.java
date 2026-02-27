package dev.sashanara.eventmanager.Users.Initialazers;

import dev.sashanara.eventmanager.Users.Role;
import dev.sashanara.eventmanager.Users.UserEntity;
import dev.sashanara.eventmanager.Users.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInitializer {

    private Logger log =  LoggerFactory.getLogger(UserInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.password}")
    private String userPassword;

    public UserInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void UserInit() {

        log.info("UserInit start");

        if (userRepository.findByLogin("user") == null){

            String hashedPassword = passwordEncoder.encode(userPassword);

            UserEntity user = new UserEntity(
                    "user",
                    hashedPassword,
                    Role.USER
            );

            userRepository.save(user);

            log.info("UserInit save user");
        }

    }

}
