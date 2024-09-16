package com.matrix.duoc_springboot_hotelmanagement_ms.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail {
  private Long bookingId;
  private Client client;
  private Room room;
  private BookingStatus status;
  private LocalDateTime bookingRequestDate;
  private LocalDateTime bookingStartDate;
  private LocalDateTime bookingEndDate;
  private BigDecimal fee;
}
