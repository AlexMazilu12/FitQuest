package com.fontys.fitquest.domain.requests;

import java.math.BigDecimal;

public class CreateUserTrainerRelationRequest {
    private Long trainerId;
    private Long userId;
    private BigDecimal price;

    // Getters
    public Long getTrainerId() {
        return trainerId;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }
}