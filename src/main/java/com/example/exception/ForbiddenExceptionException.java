package com.example.exception;

/**
 * The type Resource not found.
 */
public class ForbiddenExceptionException extends RuntimeException {
  /**
   *
   */
  private static final long serialVersionUID = -671230323508425091L;

  /**
   * Instantiates a new Resource not found.
   *
   * @param msg the msg
   */
  public ForbiddenExceptionException(String msg) {
    super(msg);
  }

  /**
   * Instantiates a new Resource not found.
   *
   * @param detalle   the detalle
   * @param exception the exception
   */
  public ForbiddenExceptionException(String detalle, Throwable exception) {
    super(detalle);
  }
}
