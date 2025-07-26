package com.zredi.wmrms.shared.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AppUserResponseDTO {

  
  private Integer userId;

  private String firstName;
  

  private String middleName;
  
  
  private String lastName;
  
  private AppRoleResponseDTO appRole;
  
  
  
}
