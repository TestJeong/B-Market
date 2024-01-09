package com.side.bmarket.domain.user.repository;

import com.side.bmarket.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserEmail(String username);
}
