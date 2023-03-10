package org.quiz.app.database;

import org.quiz.app.entities.User;
import org.quiz.app.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Repository<String, User> {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public User findById(String id) {
        if (id != null && users.get(id) != null) {
            return users.get(id);
        }
        throw new EntityNotFoundException("Can't find user with id " + id);
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public boolean existByEmail(String email) {
        return users.values().stream()
                .anyMatch(o -> o.getEmail().equals(email));
    }
}

