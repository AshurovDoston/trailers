package com.crasoftinc.jmatrailers.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex,
                                                     WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {CustomGeneralException.class})
  public ResponseEntity<Object> generalExceptionHandler(CustomGeneralException ex,
                                                        WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {InvalidRequestException.class})
  public ResponseEntity<Object> handleInvalidRequests(InvalidRequestException ex,
                                                      WebRequest request) {
    CustomErrorModel response =
        new CustomErrorModel(ex.getMessage(), ex.getCode(), HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponse error = new ErrorResponse("Validation failed", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

}
