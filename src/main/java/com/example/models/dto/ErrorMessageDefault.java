package com.example.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorMessageDefault {

  private String exception;
  private String message;
  private String path;

  public ErrorMessageDefault(Exception exception, String path){
    this.exception = exception.getClass().getSimpleName();
    this.message = exception.getMessage();
    this.path = path;
  }
}
