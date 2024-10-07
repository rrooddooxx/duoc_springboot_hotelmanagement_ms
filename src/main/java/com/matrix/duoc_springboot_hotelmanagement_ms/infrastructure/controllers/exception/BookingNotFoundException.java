package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends RuntimeException {

  public BookingNotFoundException(String message) {
    super(message);
  }
}
