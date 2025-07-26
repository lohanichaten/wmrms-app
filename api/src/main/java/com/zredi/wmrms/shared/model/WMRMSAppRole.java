package com.zredi.wmrms.shared.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="wrms_app_role")
@EntityListeners(AuditingEntityListener.class)
@Data
public class WMRMSAppRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="role_id")
  private Integer roleId;
  
  @Column(name="role_name")
  private String roleName;
  
  @Column(name="role_desc")
  private String roleDesc;
  
  
  @OneToMany(cascade = CascadeType.DETACH)
  @JoinTable(
      name = "wrms_app_role_permission",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id")
  )
  private Set<WMRMSAppPermission> permissions=new HashSet<>();
  
  
  @CreatedBy
  @Column(name="created_by")
  private Integer createdBy;
  
  @CreatedDate
  @Column(name="created_datetime")
  private Instant createdDatetime;
  
  @LastModifiedBy
  @Column(name="last_modified_by")
  private Integer lastModifiedBy;
  
  @LastModifiedDate
  @Column(name="last_modified_datetime")
  private Instant lastModifiedDatetime;
  
}
