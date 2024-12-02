CREATE TABLE IF NOT EXISTS workout_plans (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             user_id BIGINT NOT NULL,
                                             title VARCHAR(255) NOT NULL,
                                             description TEXT,
                                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
                                             FOREIGN KEY (user_id) REFERENCES users(id)
);