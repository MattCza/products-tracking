package com.example.ProductsTracker.Service;

import com.example.ProductsTracker.Repository.UserRepository;
import com.example.ProductsTracker.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String PEPPER = "g00d p3pp3r 15 n07 84d";
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){ return userRepository.findAll(); }
    public User saveUser(User user) {
        StringBuilder stringBuilder = new StringBuilder(user.getPassword());
        stringBuilder.append(PEPPER);
        user.setPassword(bCrypt.encode(stringBuilder));
        return userRepository.save(user);
    }

    public Boolean isUserPresentInDb(String email){
        Optional<User> userFromDb = userRepository.findByEmail(email);
        return userFromDb.isPresent();
    }

    public Boolean authenticationUser(String email, String password){
        Optional<User> userFromDb = userRepository.findByEmail(email);
        StringBuilder stringBuilder = new StringBuilder(password);
        return userFromDb.map(user -> bCrypt.matches(stringBuilder.append(PEPPER), user.getPassword())).orElse(false);
    }


}
