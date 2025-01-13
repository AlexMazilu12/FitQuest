package com.fontys.fitquest.domain.requests;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CreateUserTrainerRelationRequest {
    private Long trainerId;
    private Long userId;
    private BigDecimal price;

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