package com.geekspring.HW.repository;

import com.geekspring.HW.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}
