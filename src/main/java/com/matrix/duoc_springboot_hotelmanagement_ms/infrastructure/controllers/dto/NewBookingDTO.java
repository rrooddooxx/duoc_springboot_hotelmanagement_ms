package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NewBookingDTO {

  @NotNull
  @Min(1)
  @JsonProperty("client_id")
  private Long clientId;

  @NotNull
  @Min(1)
  @JsonProperty("room_id")
  private Long roomId;

  @JsonProperty("date_request")
  private LocalDateTime bookingRequestDate;

  @JsonProperty("date_start")
  private LocalDateTime bookingStartDate;

  @JsonProperty("date_end")
  private LocalDateTime bookingEndDate;

  @Min(1000)
  @JsonProperty("fee")
  private BigDecimal fee;
}
