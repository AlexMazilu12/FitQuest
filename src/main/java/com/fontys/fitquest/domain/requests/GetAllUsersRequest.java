package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersRequest {
    private String email;
    private Role role;
}
