package com.example.springbootdateisodemo.error;

public class UserNotFoundException extends RuntimeException {

  /** */
  private static final long serialVersionUID = -7856626218086382625L;

  public UserNotFoundException(Integer id) {
    super("User id not found : " + id);
  }
}
