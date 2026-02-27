package dev.sashanara.eventmanager.Users;

public record User (

        Long id,
        String login,
        String password,
        Role role

) {

}
