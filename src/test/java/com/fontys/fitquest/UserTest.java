package com.fontys.fitquest;

import com.fontys.fitquest.business.implementation.UserConverter;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void getName_shouldReturnUserName() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123", Role.USER);

        // act
        String actualName = user.getName();

        // assert
        assertEquals("John Doe", actualName);
    }

    @Test
    void getEmail_shouldReturnUserEmail() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123", Role.USER);

        // act
        String actualEmail = user.getEmail();

        // assert
        assertEquals("john@example.com", actualEmail);
    }

    @Test
    void setPassword_shouldChangeUserPassword() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123", Role.TRAINER);

        // act
        user.setPassword("newPassword456");
        String actualPassword = user.getPassword();

        // assert
        assertEquals("newPassword456", actualPassword);
    }

    @Test
    void getId_shouldReturnUserId() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123", Role.ADMIN);

        // act
        Long actualId = user.getId();

        // assert
        assertEquals(1L, actualId);
    }

    @Test
    void convert_shouldConvertUserEntityToUser() {
        // Arrange
        UserEntity userEntity = new UserEntity(1L, "john", "john@example.com", "password123", Role.USER);

        // Act
        User user = UserConverter.convert(userEntity);

        // Assert
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("john", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(Role.USER, user.getRole());
    }
}
