package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDTO {
  @JsonProperty("booking_id")
  private String bookingId;

  @JsonProperty("client_id")
  private Long clientId;

  @JsonProperty("client_id")
  private Integer roomId;

  @JsonProperty("date_request")
  private LocalDateTime bookingRequestDate;

  @JsonProperty("date_start")
  private LocalDateTime bookingStartDate;

  @JsonProperty("date_end")
  private LocalDateTime bookingEndDate;

  @JsonProperty("fee")
  private BigDecimal fee;
}
