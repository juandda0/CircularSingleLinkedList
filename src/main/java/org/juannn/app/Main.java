package org.juannn.app;

import org.juannn.data_structures.CircularSinglyLinkedList;
import org.juannn.models.User;
import org.juannn.repository.UserRepository;
import org.juannn.repository.impl.UserRepositoryImpl;
import org.juannn.services.UserService;

public class Main {
    public static void main(String[] args) {
        // Crear una lista enlazada circular y el repositorio de usuarios
        CircularSinglyLinkedList<User> userList = new CircularSinglyLinkedList<>();
        UserRepository userRepository = new UserRepositoryImpl(userList);
        UserService userService = new UserService(userRepository);

        // Crear algunos usuarios
        User user1 = new User(1L, "John Doe");
        User user2 = new User(2L, "Jane Doe");
        User user3 = new User(3L, "Alice Smith");

        // Guardar usuarios
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);

        // Mostrar todos los usuarios
        System.out.println("Usuarios guardados:");
        userService.findAll().forEach(System.out::println);

        // Buscar un usuario por ID
        System.out.println("\nBuscando usuario con ID 2:");
        User foundUser = userService.findById(2L);
        System.out.println(foundUser);

        // Actualizar un usuario
        System.out.println("\nActualizando el nombre del usuario con ID 1:");
        User updatedUser = new User(1L, "Johnathan Doe");
        userService.update(updatedUser);
        userService.findAll().forEach(System.out::println);

        // Verificar si un usuario existe por ID
        System.out.println("\n¿Existe usuario con ID 3?");
        boolean exists = userService.existsById(3L);
        System.out.println("Exists: " + exists);

        // Contar el número de usuarios
        System.out.println("\nNúmero total de usuarios:");
        Long count = userService.count();
        System.out.println("Count: " + count);

        // Eliminar un usuario por ID
        System.out.println("\nEliminando usuario con ID 2:");
        userService.delete(2L);
        userService.findAll().forEach(System.out::println);

        // Intentar encontrar un usuario eliminado
        try {
            System.out.println("\nBuscando usuario con ID 2:");
            userService.findById(2L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
