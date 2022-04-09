package com.devsuperior.bds03.services.repositories;

import com.devsuperior.bds03.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
