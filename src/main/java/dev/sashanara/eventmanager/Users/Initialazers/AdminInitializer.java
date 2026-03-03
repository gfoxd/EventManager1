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
public class AdminInitializer {

    private Logger log =  LoggerFactory.getLogger(AdminInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String adminPassword;

    public AdminInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void AdminInit() {

        log.info("AdminInit start");

        if (userRepository.findByLogin("admin") == null){

            String hashedPassword = passwordEncoder.encode(adminPassword);

            UserEntity admin = new UserEntity(
                    "admin",
                    hashedPassword,
                    null,
                    Role.ADMIN
            );

            userRepository.save(admin);

            log.info("AdminInit save admin");
        }

    }

}
