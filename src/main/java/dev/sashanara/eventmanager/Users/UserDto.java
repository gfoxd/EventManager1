package dev.sashanara.eventmanager.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record UserDto (

        @Null
        Long id,

        @NotBlank
        @Size(min = 3, max = 30)
        String login,

        @Size(min = 5, max = 30)
        String password,

        @Null
        Role role

) {

}