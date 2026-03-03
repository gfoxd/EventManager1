package dev.sashanara.eventmanager.Users;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toDomain(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.login(),
                userDto.password(),
                userDto.age(),
                userDto.role()
        );
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                null,
                userEntity.getAge(),
                userEntity.getRole()
        );
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.id(),
                user.login(),
                null,
                user.age(),
                user.role()
        );
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.login(),
                null,
                user.age(),
                user.role()
        );
    }
}
