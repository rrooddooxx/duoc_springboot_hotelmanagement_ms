package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class NewClientResponseDTO {
  @JsonProperty("client_id")
  Long clientId;

  @JsonProperty("status")
  String status;
}
