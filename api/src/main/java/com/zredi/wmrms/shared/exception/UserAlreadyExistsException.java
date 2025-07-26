package com.zredi.wmrms.shared.exception;

public class UserAlreadyExistsException extends BadRequestException{

  public UserAlreadyExistsException(String message) {
    super(message);
    
  }

}
