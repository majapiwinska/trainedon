package org.example.ws.service;

import org.example.ws.model.Training;
import org.example.ws.model.User;

import java.util.Collection;

/**
 * Created by maja on 19.10.16.
 */
public interface UserService {

    User findUserById(Long id);

    User findUserByEmail(String email);

    Collection<User> findAll();

    User create(User user);

    User update(User user);

    void delete(Long id);

    User addTrainingToUser(User user, Training training);
}
