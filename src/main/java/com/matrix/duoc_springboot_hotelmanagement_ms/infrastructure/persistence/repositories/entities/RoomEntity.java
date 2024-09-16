package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.AvailabilityStatus;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.RoomType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "room")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long roomId;

  @Column(name = "room_type", nullable = false)
  private RoomType roomType;

  @Column(name = "availability_status", nullable = false)
  private AvailabilityStatus availabilityStatus;

  @Column(name = "guest_count", nullable = false)
  private int guestCount;

  @Column(name = "bed_count", nullable = false)
  private int bedCount;

  @Column(name = "price", nullable = false)
  private BigDecimal price;
}
