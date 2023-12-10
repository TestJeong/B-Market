package com.side.bmarket.domain.user.repository;

import com.side.bmarket.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
