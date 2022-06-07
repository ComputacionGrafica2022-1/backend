package com.aaa.backend.Controllers;

import java.util.ArrayList;

import com.aaa.backend.Models.User;
import com.aaa.backend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String welcome(){
        return "Hello world!";
    }

    @GetMapping("/users")
    public ArrayList<User> getAllUsers(){
        return userService.getAllUsers();
    }
    
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        if(userService.getUserbyEmail(user.getEmail()) == null){
            return userService.saveUser(user);
        }
        return null;
    }

    @GetMapping("/user/{id}")
    public User getUserbyId(@PathVariable("id") Long id){
        return userService.getUserbyId(id);
    }
   
    @GetMapping("/user/query")
    public User getUserbyEmail(@RequestParam("email") String email){
        return userService.getUserbyEmail(email);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserbyId(@PathVariable("id") Long id){
        boolean ok = userService.deleteUser(id);

        if(ok){
            return "User deleted successfully";
        }else{
            return "Failed to delete user";
        }
    }

}
