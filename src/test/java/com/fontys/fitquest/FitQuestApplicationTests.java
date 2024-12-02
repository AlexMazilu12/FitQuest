package com.fontys.fitquest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FitQuestApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully.
        // It is intentionally left empty because the presence of this method is enough
        // to trigger the context loading process.
    }
}