package com.zredi.wmrms.shared.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
  
  @NotEmpty(message="Value required for New Password")
  private String newPassword;
  
  @NotEmpty(message ="Value required for confirm password")
  private String confirmNewPassword;

}
