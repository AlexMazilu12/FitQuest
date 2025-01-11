package com.fontys.fitquest.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUsersForTrainerResponse {
    private Long id;
    private String name;
    private String email;
}