package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findById(Long userId);
    Optional<UserEntity> findByEmail(String email);
}
