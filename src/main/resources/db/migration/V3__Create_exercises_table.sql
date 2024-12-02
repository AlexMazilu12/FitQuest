CREATE TABLE IF NOT EXISTS exercises(
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL, -- Exercise name (e.g., "Bench Press")
                           muscle_group VARCHAR(50) NOT NULL, -- Targeted muscle group (e.g., "Chest")
                           description TEXT, -- Optional detailed description
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);