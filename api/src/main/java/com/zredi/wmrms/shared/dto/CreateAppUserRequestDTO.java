package com.zredi.wmrms.shared.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAppUserRequestDTO {

@NotNull(message="Required Field")
  private Integer userId;
  
  @NotEmpty(message="Required Field")
  private String password;
  
  @NotEmpty(message="Required Field")
  private String confirmPassword;
  
  @NotEmpty(message="Required Field")
  private String firstName;
  private String middleName;
  private String lastName;
  
  @NotNull(message="Required Field")
  private Integer appRoleId;
  
}
