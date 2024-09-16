package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception;

import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.ResourceNotFoundExceptionDTO;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.RoomNotAvailableExceptionDTO;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

  @ExceptionHandler(value = RoomNotAvailableException.class)
  public ResponseEntity<RoomNotAvailableExceptionDTO> handleRoomNotAvailableException(
      RoomNotAvailableException exception) {
    log.error("Exception Handled! RoomNotAvailableException: {}", exception.getMessage());
    String[] msgs = exception.getMessage().split("\\|");
    RoomNotAvailableExceptionDTO response =
        RoomNotAvailableExceptionDTO.builder().errorMsg(msgs[0]).roomStatus(msgs[1].trim()).build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ResourceNotFoundExceptionDTO> handleResourceNotFoundException(
      ResourceNotFoundException exception) {
    ResourceNotFoundExceptionDTO response =
        ResourceNotFoundExceptionDTO.builder().errorMsg(exception.getMessage()).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleEnumParsingException(
      HttpMessageNotReadableException exception) {
    HashMap<String, String> response = new HashMap<>();
    response.put("error_msg", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
