package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long userId);
}
