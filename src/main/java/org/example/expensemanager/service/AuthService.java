package org.example.expensemanager.service;

import org.example.expensemanager.exception.UserWithGivenNameAlreadyExistException;
import org.example.expensemanager.model.User;
import org.example.expensemanager.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(User user) {
        User dbUser = userRepository.getUserByUserName(user.getUsername());
        if (dbUser.getUserId() == null) return null;
        if (BCrypt.checkpw(user.getPassword(), dbUser.getPassword())) {
            return dbUser;
        } else {
            return null;
        }
    }

    public void register(User user) {
        if (userRepository.getUserByUserName(user.getUsername()).getUserId() != null) {
            throw new UserWithGivenNameAlreadyExistException("User with given username already exists");
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.addUser(user);
    }
}
