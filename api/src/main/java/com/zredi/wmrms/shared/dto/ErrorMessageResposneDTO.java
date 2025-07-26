package com.zredi.wmrms.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessageResposneDTO {
  
  private String errorField;
  private String errorMessage;

}
