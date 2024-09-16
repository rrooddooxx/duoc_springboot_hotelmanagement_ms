package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateBookingStatusDto {

  @Enumerated(EnumType.STRING)
  @NotNull
  private BookingStatus status;
}
