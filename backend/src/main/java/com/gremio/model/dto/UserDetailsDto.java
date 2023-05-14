package com.gremio.model.dto;

import com.gremio.enums.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {
    private Long id;
    private String email;
    private RoleType role;

}