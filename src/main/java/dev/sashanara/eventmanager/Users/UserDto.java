package dev.sashanara.eventmanager.Users;

import jakarta.validation.constraints.*;

public record UserDto (

        @Null
        Long id,

        @NotBlank
        @Size(min = 3, max = 30)
        String login,

        @Size(min = 5, max = 30)
        String password,

        @NotNull
        Integer age,

        @Null
        Role role

) {

}