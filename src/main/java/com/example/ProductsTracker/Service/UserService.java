package com.example.ProductsTracker.Service;

import com.example.ProductsTracker.Repository.UserRepository;
import com.example.ProductsTracker.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String PEPPER = "g00d p3pp3r 15 n07 84d";
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }




    public List<User> getAllUsers(){ return userRepository.findAll(); }
    public User saveUser(User user) {
        user.setPassword(bCrypt.encode(user.getPassword() + PEPPER));
        return userRepository.save(user);
    }



    private Optional<User> getUserFromDb(User user){
        return userRepository.findByEmail(user.getEmail());
    }

    public Boolean isUserPresentInDb(String email){
        Optional<User> userFromDb = userRepository.findByEmail(email);
        return userFromDb.isPresent();
    }

    public Boolean authenticationUser(String email, String password){
        Optional<User> userFromDb = userRepository.findByEmail(email);
        return userFromDb.map(user -> bCrypt.matches(password + PEPPER, user.getPassword())).orElse(false);
    }


}
