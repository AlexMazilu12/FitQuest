CREATE TABLE IF NOT EXISTS booking_requests (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  user_id BIGINT NOT NULL, -- References users(id)
                                  user_name VARCHAR(100) NOT NULL, -- Redundant but included for quick access
                                  trainer_id BIGINT NOT NULL, -- References users(id) where role = 'TRAINER'
                                  message TEXT, -- Message provided by the user
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- When the request was created
                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                  FOREIGN KEY (trainer_id) REFERENCES users(id)
);