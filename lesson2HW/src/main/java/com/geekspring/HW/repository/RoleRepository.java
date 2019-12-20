package com.geekspring.HW.repository;

import com.geekspring.HW.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findOneByName(String name);
}
