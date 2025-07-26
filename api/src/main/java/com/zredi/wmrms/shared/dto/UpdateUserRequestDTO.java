package com.zredi.wmrms.shared.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {

  
  @NotEmpty(message="Required Field")
  private String firstName;
  private String middleName;
  private String lastName;
  
  @NotNull
  private Integer appRoleId;
  
}
