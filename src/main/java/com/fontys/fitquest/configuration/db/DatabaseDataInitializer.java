package com.fontys.fitquest.configuration.db;

import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {
    private UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (userRepository.count() == 0) {
            userRepository.save(UserEntity.builder().name("User1").email("user1@gmail.com").password("123").role(Role.USER).build());
            userRepository.save(UserEntity.builder().name("User2").email("user2@gmail.com").password("123").role(Role.TRAINER).build());
            userRepository.save(UserEntity.builder().name("User3").email("user3@gmail.com").password("123").role(Role.USER).build());
            userRepository.save(UserEntity.builder().name("User4").email("user4@gmail.com").password("123").role(Role.USER).build());
            userRepository.save(UserEntity.builder().name("User5").email("user5@gmail.com").password("123").role(Role.ADMIN).build());
        }
    }
}
