package com.example.exception;

/**
 * The type Resource not found.
 */
public class JWTException extends RuntimeException {
  /**
   *
   */
  private static final long serialVersionUID = -6719930323508425091L;

  /**
   * Instantiates a new Resource not found.
   *
   * @param msg the msg
   */
  public JWTException(String msg) {
    super(msg);
  }

  /**
   * Instantiates a new Resource not found.
   *
   * @param detalle   the detalle
   * @param exception the exception
   */
  public JWTException(String detalle, Throwable exception) {
    super(detalle);
  }
}
