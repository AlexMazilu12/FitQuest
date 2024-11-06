USE fitquest_db;
CREATE TABLE IF NOT EXISTS roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50),
                       FOREIGN KEY (role) REFERENCES roles(role_name)
);

INSERT INTO roles (role_name) VALUES ('TRAINEE'), ('TRAINER');
INSERT INTO users (name, email, password, role) VALUES
('John Doe', 'john.doe@example.com', 'encrypted_password', 'TRAINEE'),
('Jane Smith', 'jane.smith@example.com', 'encrypted_password', 'TRAINER');

SHOW TABLES;