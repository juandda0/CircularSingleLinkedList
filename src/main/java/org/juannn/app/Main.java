package org.juannn.app;

import org.juannn.data_structures.CircularSinglyLinkedList;
import org.juannn.models.User;
import org.juannn.repository.UserRepository;
import org.juannn.repository.impl.UserRepositoryImpl;
import org.juannn.services.UserService;

public class Main {
    public static void main(String[] args) {
        // Create a circular singly linked list and the user repository
        CircularSinglyLinkedList<User> userList = new CircularSinglyLinkedList<>();
        UserRepository userRepository = new UserRepositoryImpl(userList);
        UserService userService = new UserService(userRepository);

        // Create some users
        User user1 = new User(1L, "John Doe");
        User user2 = new User(2L, "Jane Doe");
        User user3 = new User(3L, "Alice Smith");

        // Save users
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);

        // Display all users
        System.out.println("Saved users:");
        userService.findAll().forEach(System.out::println);

        // Find a user by ID
        System.out.println("\nSearching for user with ID 2:");
        User foundUser = userService.findById(2L);
        System.out.println(foundUser);

        // Update a user
        System.out.println("\nUpdating the name of the user with ID 1:");
        User updatedUser = new User(1L, "Johnathan Doe");
        userService.update(updatedUser);
        userService.findAll().forEach(System.out::println);

        // Check if a user exists by ID
        System.out.println("\nDoes a user with ID 3 exist?");
        boolean exists = userService.existsById(3L);
        System.out.println("Exists: " + exists);

        // Count the number of users
        System.out.println("\nTotal number of users:");
        Long count = userService.count();
        System.out.println("Count: " + count);

        // Delete a user by ID
        System.out.println("\nDeleting user with ID 2:");
        userService.delete(2L);
        userService.findAll().forEach(System.out::println);

        // Attempt to find a deleted user
        try {
            System.out.println("\nSearching for user with ID 2:");
            userService.findById(2L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
