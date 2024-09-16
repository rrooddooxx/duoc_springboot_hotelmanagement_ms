package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceErrorException extends RuntimeException {
  public ServiceErrorException(String message) {
    super(message);
  }
}
