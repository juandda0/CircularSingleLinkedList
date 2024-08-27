package org.juannn.services;

import org.juannn.data_structures.CircularSinglyLinkedList;
import org.juannn.models.User;
import org.juannn.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public CircularSinglyLinkedList<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User updatedUser) {
        userRepository.update(updatedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public Long count() {
        return userRepository.count();
    }
}
