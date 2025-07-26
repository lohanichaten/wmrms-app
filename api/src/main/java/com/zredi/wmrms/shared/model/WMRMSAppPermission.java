package com.zredi.wmrms.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="wmrms_app_permission")
@Data
public class WMRMSAppPermission {

  
  @Id
  @Column(name="permission_id")
  private Integer permissionId;
  
  @Column(name="permission_name")
  private String permissionName;
  
  @Column(name="permission_desc")
  private String permissionDescription;
  
}
