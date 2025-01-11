package com.fontys.fitquest.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "trainer_user_assignments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerUserAssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private UserEntity trainer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "assigned_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp assignedAt;

    public Long getTrainerId() {
        return trainer.getId();
    }

    public Long getUserId() {
        return user.getId();
    }
}