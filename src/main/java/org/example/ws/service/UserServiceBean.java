package org.example.ws.service;

import org.example.ws.model.Training;
import org.example.ws.model.User;
import org.example.ws.repository.TrainingRepository;
import org.example.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
/**
 * Created by maja on 19.10.16.
 */


@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class UserServiceBean implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public User findUserById(Long id) {
        User user = userRepository.findOne(id);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public User findUserByEmail(String  email) {
        User user = userRepository.getUserByEmail(email);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public Collection<User> findAll() {
        Collection<User> users = userRepository.findAll();
        return users;
    }

   /* @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public User create(User user) {
        if(user.getId() != null ){
            return null;
        }
        User createdUser = userRepository.save(user);
        return createdUser;
    }*/



    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public User create(User user) {
        if(user.getId() != null ){
            return null;
        }
        User createdUser = new User();
        createdUser.setFirstName(user.getFirstName());
        createdUser.setLastName(user.getLastName());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        createdUser.setRoles(user.getRoles());
        return userRepository.save(createdUser);

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public User update(User user) {
       User userPersisted = findUserById(user.getId());
       if(userPersisted == null){
           return null;
       }

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public void delete(Long id) {
       userRepository.delete(id);

    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED,
            readOnly = false)
    public User addTrainingToUser(User user, Training training){
        if(trainingRepository.findOne(training.getId()) == null || userRepository.findOne(user.getId()) == null)
            return null;
        user.getTrainingList().add(training);
        return userRepository.save(user);
    };
}
