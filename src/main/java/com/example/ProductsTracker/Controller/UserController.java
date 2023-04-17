package com.example.ProductsTracker.Controller;

import com.example.ProductsTracker.Service.UserService;
import com.example.ProductsTracker.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(userService.isUserPresentInDb(user.getEmail())){
            return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("email") String email, @RequestParam("password") String password){
        if (userService.authenticationUser(email, password)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
