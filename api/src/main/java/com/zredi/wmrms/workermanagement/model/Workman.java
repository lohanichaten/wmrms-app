package com.zredi.wmrms.workermanagement.model;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="wmrms_workman")
public class Workman {

  
        @Id
        @Column(name="emp_no")
        private Integer empNumber;
        
        
        @Column(name="status")
        private String status;
        
        
        @Column(name="first_name")
        private String firstName;
        
        
        @Column(name="middle_name")
        private String middleName;
        
        
        @Column(name="last_name")
        private String lastName;
        
        
        
        @Column(name="adhar_no")
        private Long adharNumber;
        
        
        
        @Column(name="dob")
        private Date dateOfBirth;
        
        
        @Column(name="doj")
        private Date dateOfJoining;
        
        @ManyToOne
        @JoinColumn(name="category_id")
        private WMRMSCategory category;
        
        
        @ManyToOne
        @JoinColumn(name="dept_id")
        private WMRMSDepartment department;
        
        
        @Column(name="identity_mark")
        private String identityMark;
  
        
        
}
