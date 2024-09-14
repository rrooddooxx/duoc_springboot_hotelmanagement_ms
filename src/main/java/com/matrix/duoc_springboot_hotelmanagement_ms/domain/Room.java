package com.matrix.duoc_springboot_hotelmanagement_ms.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
  private Long roomId;
  private RoomType roomType;
  private AvailabilityStatus availabilityStatus;
  private int guestCount;
  private int bedCount;
  private BigDecimal price;
}
