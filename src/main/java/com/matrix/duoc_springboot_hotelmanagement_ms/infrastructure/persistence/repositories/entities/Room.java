package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
public class Room {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer roomId;

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
