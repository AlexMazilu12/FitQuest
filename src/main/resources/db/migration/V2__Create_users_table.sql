CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL UNIQUE,
                                     email VARCHAR(100) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     role VARCHAR(50),
);