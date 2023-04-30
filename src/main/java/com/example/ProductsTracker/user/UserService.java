package com.example.ProductsTracker.user;

import com.example.ProductsTracker.exception.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String PEPPER = "g00d p3pp3r 15 n07 84d";
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User", "ID", id));
    }

    public User saveUser(User user) {
        user.setPassword(bCrypt.encode(passwordGeneration(user.getPassword())));
        return userRepository.save(user);
    }

    public Boolean isUserPresentInDb(String email) {
        Optional<User> userFromDb = userRepository.findByEmail(email);
        return userFromDb.isPresent();
    }

    public Boolean authenticationUser(String email, String password) {
        Optional<User> userFromDb = userRepository.findByEmail(email);
        return userFromDb.map(user -> bCrypt.matches(passwordGeneration(password), user.getPassword())).orElse(false);
    }

    public User patchUser(User user, Integer id) {
        User currentUser = getUserById(id);
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(passwordGeneration(user.getPassword()));

        if (user.getFirstName() != null) {
            currentUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            currentUser.setLastName(user.getLastName());
        }

        saveUser(currentUser);
        return currentUser;
    }

    public void deleteUser(Integer id) {
        getUserById(id);
        userRepository.deleteById(id);
    }


    private String passwordGeneration(String password) {
        StringBuilder stringBuilder = new StringBuilder(password);
        stringBuilder.append(PEPPER);
        return bCrypt.encode(stringBuilder);
    }


}
