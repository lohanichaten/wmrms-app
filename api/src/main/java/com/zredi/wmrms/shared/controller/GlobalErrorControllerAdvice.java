package com.zredi.wmrms.shared.controller;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zredi.wmrms.shared.dto.ErrorMessageResposneDTO;
import com.zredi.wmrms.shared.exception.BadCredentailsException;
import com.zredi.wmrms.shared.exception.BadRequestException;
import com.zredi.wmrms.shared.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorControllerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    ProblemDetail problemDetail = handleValidationException(ex);
    return ResponseEntity.status(status.value()).body(problemDetail);
  }

  private ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
    String details = getErrorsDetails(ex);
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), details);
    problemDetail.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
    problemDetail.setInstance(ex.getBody().getInstance());
    problemDetail.setProperty("timestamp", Instant.now()); // adding more data using the Map properties of the
    problemDetail.setProperty("errors", getFieldValidationError(ex));                                                     // ProblemDetail
    return problemDetail;
  }

  private String getErrorsDetails(MethodArgumentNotValidException ex) {
    return Optional.ofNullable(ex.getDetailMessageArguments())
        .map(args -> Arrays.stream(args).filter(msg -> !ObjectUtils.isEmpty(msg))
            .reduce("Please make sure to provide a valid request, ", (a, b) -> a + " " + b))
        .orElse("").toString();
  }

  private List<ErrorMessageResposneDTO> getFieldValidationError(MethodArgumentNotValidException ex) {
    List<ErrorMessageResposneDTO> errors=new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.add( ErrorMessageResposneDTO.builder()
                                      .errorField(error.getField())
                                      .errorMessage(error.getDefaultMessage()).build()));

    return errors;
  }
  
  @ExceptionHandler({BadCredentailsException.class,ResourceNotFoundException.class,BadRequestException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProblemDetail handleAuthenticatioException(Exception ex,WebRequest request,HttpServletRequest httpRequest) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
    problemDetail.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
    problemDetail.setDetail(ex.getMessage()); 
    problemDetail.setInstance(URI.create(httpRequest.getRequestURI()));
    problemDetail.setProperty("timestamp", Instant.now());
    return problemDetail;
  }

  
  
  @ExceptionHandler
  ProblemDetail handleUncaughtException(ServletWebRequest request, RuntimeException e) {
      log.error("Unexpected error while handling {}", request.getRequest().getRequestURI(), e);
      return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
  }
  
  
}
