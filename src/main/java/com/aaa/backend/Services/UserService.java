package com.aaa.backend.Services;

import java.util.ArrayList;

import com.aaa.backend.Models.User;
import com.aaa.backend.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
    
    public User getUserbyId(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserbyEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
