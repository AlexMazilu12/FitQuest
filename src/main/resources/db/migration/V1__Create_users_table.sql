CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     email VARCHAR(100) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     role ENUM('USER','TRAINER', 'ADMIN') DEFAULT 'USER',
                                     name VARCHAR(100),
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);