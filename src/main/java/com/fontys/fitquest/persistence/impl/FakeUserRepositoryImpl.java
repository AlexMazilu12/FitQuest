package com.fontys.fitquest.persistence.impl;

import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {
    private static long NEXT_ID = 1;
    private final List<UserEntity> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public boolean existsByName(String name) {
        return this.savedUsers
                .stream()
                .anyMatch(studentEntity -> studentEntity.getName().equals(name));
    }

    @Override
    public UserEntity save(UserEntity student) {
        return null;
    }

    @Override
    public void deleteById(long studentId) {
        this.savedUsers.removeIf(studentEntity -> studentEntity.getId().equals(studentId));
    }

    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(this.savedUsers);
    }

    @Override
    public Optional<UserEntity> findById(long studentId) {
        return this.savedUsers.stream()
                .filter(studentEntity -> studentEntity.getId().equals(studentId))
                .findFirst();
    }

    @Override
    public int count() {
        return this.savedUsers.size();
    }
}
