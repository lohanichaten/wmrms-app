package com.zredi.wmrms.workermanagement.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateNewWorkmanRequestDTO {

  
  @NotNull
  private Integer empNumber;
  
  
  @NotEmpty(message="Required field")
  private String firstName;

  
  private String middleName;
  
  
  private String lastName;
  
  @NotNull
  @Size(min = 12,max=12)
  private Long adharNo;
  
  
  @NotNull
  @DateTimeFormat(pattern = "dd-mm-yyyy")
  private Date dateOfBirth;
  
  @NotNull
  @DateTimeFormat(pattern = "dd-mm-yyyy")
  private Date dateOfJoining;
  
  @NotNull(message="Required Field")
  private Integer departmentId;
  
  @NotNull(message="Require Field")
  private Integer categoryId;
  
  @NotEmpty(message="Required Field")
  private String identityMark;
}


