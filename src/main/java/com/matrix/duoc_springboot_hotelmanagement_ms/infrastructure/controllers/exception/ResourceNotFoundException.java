package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
    log.error("FAILED! Resource not found | Reason: {}", message);
  }
}
