package com.example.ProductsTracker.Service;

import com.example.ProductsTracker.Repository.UserRepository;
import com.example.ProductsTracker.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){ return userRepository.findAll(); }
    public User saveUser(User user) {return userRepository.save(user);}

    public Boolean uniqueEmail (User user){
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());
        return userFromDb.isEmpty();
    }
}
