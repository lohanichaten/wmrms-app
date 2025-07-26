package com.zredi.wmrms.shared.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ModifyAppRoleRequest {
  
  @NotEmpty(message="Required Field")
  private String roleName;
  private String roleDescription;
  private List<Integer> permissionIds;

}
