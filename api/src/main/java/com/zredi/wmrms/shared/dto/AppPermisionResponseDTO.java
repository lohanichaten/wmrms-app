package com.zredi.wmrms.shared.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AppPermisionResponseDTO {

  private Integer permissionId;
  private String permissionName;
  private String permissionDescription;
  
}
