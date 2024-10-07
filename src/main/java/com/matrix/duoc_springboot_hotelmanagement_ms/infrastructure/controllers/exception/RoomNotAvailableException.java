package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class RoomNotAvailableException extends RuntimeException {
  public RoomNotAvailableException(String message) {
    super(message);
    log.error("FAILED! Room not available | Reason: {}", message);
  }
}
