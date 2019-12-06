package com.geekspring.HW.service;

import com.geekspring.HW.entity.User;
import com.geekspring.HW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User save(User user){
        return repository.save(user);
    }
}
