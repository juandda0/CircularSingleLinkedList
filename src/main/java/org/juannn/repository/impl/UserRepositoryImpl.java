package org.juannn.repository.impl;

import org.juannn.data_structures.CircularSinglyLinkedList;
import org.juannn.models.User;
import org.juannn.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    CircularSinglyLinkedList<User> userList;

    public UserRepositoryImpl(CircularSinglyLinkedList<User> userList) {
        this.userList = userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public User findById(Long id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new IllegalArgumentException("User with ID: " + id + " not found.");
    }

    @Override
    public CircularSinglyLinkedList<User> findAll() {
        return userList;
    }

    @Override
    public void update(User updatedUser) {

        for(User user : userList){
            if(user.getId().equals(updatedUser.getId())){
                user.setName(updatedUser.getName());
                return;
            }
        }
        throw new IllegalArgumentException("User with ID: " + updatedUser.getId() + " not found.");
    }

    @Override
    public void deleteById(Long id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                userList.remove(user);
                return;
            }
        }
        throw new IllegalArgumentException("User with ID: " + id + " not found.");
    }

    @Override
    public boolean existsById(Long id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Long count() {
        return (long) userList.size();
    }

}
