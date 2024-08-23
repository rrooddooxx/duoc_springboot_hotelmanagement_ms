package com.matrix.duoc_springboot_hotelmanagement_ms.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
  private String bookingId;
  private Client clientId;
  private Integer roomId;
  private LocalDateTime bookingRequestDate;
  private LocalDateTime bookingStartDate;
  private LocalDateTime bookingEndDate;
  private BigDecimal fee;
}
