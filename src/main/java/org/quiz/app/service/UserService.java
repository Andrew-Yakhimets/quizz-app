package org.quiz.app.service;

import org.quiz.app.database.UserRepository;
import org.quiz.app.entities.User;
import org.quiz.app.exceptions.InvalidDataException;
import org.quiz.app.models.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserDto userDto) {
        validate(userDto);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    public Optional<User> findUserOptionalInUserRepo(String email, String password) {
        return userRepository.findOptionalByEmailAndPassword(email, password);
    }

    private void validate(UserDto userDto) {
        if (userRepository.existByEmail(userDto.getEmail())) {
            throw new InvalidDataException("User with such email already exists");
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new InvalidDataException("Passwords don't match");
        }
    }
}
