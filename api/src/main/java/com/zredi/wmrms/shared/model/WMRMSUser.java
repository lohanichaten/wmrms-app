package com.zredi.wmrms.shared.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="wmrms_user")
@EntityListeners(AuditingEntityListener.class)

@Data
public class WMRMSUser {

  @Id
  @Column(name="user_id")
  private Integer userId;
  
  @Column(name="password")
  private String password;
  
  
  @Column(name="status")
  private String status;
  
  @Column(name="first_name")
  private String firstName;
  
  @Column(name="middle_name")
  private String middleName;
  
  @Column(name="last_name")
  private String lastName;
  
  
  @ManyToOne
  @JoinColumn(name = "role_id")
  private WMRMSAppRole appRole;
  
  
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
