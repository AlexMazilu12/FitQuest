CREATE TABLE IF NOT EXISTS workout_plan_exercises (
                                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                      workout_plan_id BIGINT NOT NULL,
                                                      exercise_id INT NOT NULL,
                                                      sets INT NOT NULL,
                                                      reps INT NOT NULL,
                                                      rest_time INT DEFAULT NULL,
                                                      FOREIGN KEY (workout_plan_id) REFERENCES workout_plans(id),
                                                      FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);