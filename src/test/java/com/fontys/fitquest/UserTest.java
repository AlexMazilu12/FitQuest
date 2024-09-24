package com.fontys.fitquest;

import com.fontys.fitquest.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void getName_shouldReturnUserName() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123");

        // act
        String actualName = user.getName();

        // assert
        assertEquals("John Doe", actualName);
    }

    @Test
    void getEmail_shouldReturnUserEmail() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123");

        // act
        String actualEmail = user.getEmail();

        // assert
        assertEquals("john@example.com", actualEmail);
    }

    @Test
    void setPassword_shouldChangeUserPassword() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123");

        // act
        user.setPassword("newPassword456");
        String actualPassword = user.getPassword();

        // assert
        assertEquals("newPassword456", actualPassword);
    }

    @Test
    void getId_shouldReturnUserId() {
        // arrange
        User user = new User(1L, "John Doe", "john@example.com", "password123");

        // act
        Long actualId = user.getId();

        // assert
        assertEquals(1L, actualId);
    }
}
