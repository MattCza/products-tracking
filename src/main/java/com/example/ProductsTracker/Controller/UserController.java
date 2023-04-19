package com.example.ProductsTracker.Controller;

import com.example.ProductsTracker.Service.UserService;
import com.example.ProductsTracker.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;
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
    public ResponseEntity<User> login(@RequestBody User user){
        if (userService.authenticationUser(user.getEmail(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
