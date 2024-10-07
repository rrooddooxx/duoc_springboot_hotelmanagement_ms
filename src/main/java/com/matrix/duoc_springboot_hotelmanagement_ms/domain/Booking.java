package com.matrix.duoc_springboot_hotelmanagement_ms.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
  private Long bookingId;
  private Long clientId;
  private Long roomId;
  private BookingStatus status;
  private LocalDateTime bookingRequestDate;
  private LocalDateTime bookingStartDate;
  private LocalDateTime bookingEndDate;
  private BigDecimal fee;
}
