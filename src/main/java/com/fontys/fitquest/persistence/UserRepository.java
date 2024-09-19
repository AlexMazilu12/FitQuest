package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByName(String name);

    UserEntity save(UserEntity student);

    void deleteById(long userId);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(long userId);

    int count();
}
