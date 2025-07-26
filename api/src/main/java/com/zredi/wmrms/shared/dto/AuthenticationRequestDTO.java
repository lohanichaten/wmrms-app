package com.zredi.wmrms.shared.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationRequestDTO {

  @NotEmpty(message = "Username is required")
  private String userName;
  
  @NotEmpty(message="Password is required")
  private String password;
}
