package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundExceptionDTO {
  @JsonProperty("error_msg")
  String errorMsg;
}
