package com.zredi.wmrms.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDTO {

    private String token;
  
}
