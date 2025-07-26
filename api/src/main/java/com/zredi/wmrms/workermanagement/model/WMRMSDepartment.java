package com.zredi.wmrms.workermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="wmrms_department")
@Data
public class WMRMSDepartment {

  
    @Id
    @Column(name="dept_id")
    private Integer departmentId;
    
    @Column(name="dept_name")
    private String departmentName;
  
}
