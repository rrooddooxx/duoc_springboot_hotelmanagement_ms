package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingStatus;

public class BookingDTO extends NewBookingDTO {
  @JsonProperty("booking_id")
  private String bookingId;

  @JsonProperty("status")
  private BookingStatus status;
}
