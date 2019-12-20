package com.geekspring.HW.service;


import com.geekspring.HW.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findByPhone(String phone);
    boolean isUserExist(String phone);
}
