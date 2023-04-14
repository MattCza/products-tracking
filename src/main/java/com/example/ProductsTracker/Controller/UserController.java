package com.example.ProductsTracker.Controller;

import com.example.ProductsTracker.Service.UserService;
import com.example.ProductsTracker.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){this.userService = userService;    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        if(!userService.uniqueEmail(user)){
            return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
