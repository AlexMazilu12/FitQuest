CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL UNIQUE,
                                     email VARCHAR(100) NOT NULL UNIQUE,
                                     password_hash VARCHAR(255) NOT NULL,
                                     role ENUM('USER','TRAINER', 'ADMIN') DEFAULT 'USER',
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);