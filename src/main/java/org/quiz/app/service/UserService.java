package org.quiz.app.service;

import org.quiz.app.database.UserRepository;
import org.quiz.app.entities.User;
import org.quiz.app.exceptions.InvalidDataException;
import org.quiz.app.models.UserDto;

import java.util.UUID;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void register(UserDto userDto) {
        if(!userRepository.existByEmail(userDto.getEmail()) && userDto.getPassword().equals(userDto.getRepeatPassword())) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
        } else {
            throw new InvalidDataException("Wrong password or User already exists");
        }
    }
}
