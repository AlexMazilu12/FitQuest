CREATE TABLE trainer_user_assignments (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          trainer_id BIGINT NOT NULL, -- References users(id) where role_id = 4 (trainer)
                                          user_id BIGINT NOT NULL, -- References users(id) where role_id = 2 (user)
                                          price DECIMAL(10, 2) NOT NULL, -- Price agreed for coaching
                                          assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- When the assignment happened
                                          FOREIGN KEY (trainer_id) REFERENCES users(id) ON DELETE CASCADE   ,
                                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);