package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "booking")
public class Booking {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private String bookingId;

  @Column(name = "client_id", nullable = false)
  private Client clientId;

  @Column(name = "room_id", nullable = false)
  private Integer roomId;

  @Column(name = "date_request", nullable = false)
  private LocalDateTime bookingRequestDate;

  @Column(name = "date_start")
  private LocalDateTime bookingStartDate;

  @Column(name = "date_end")
  private LocalDateTime bookingEndDate;

  @Column(name = "fee", nullable = false)
  private BigDecimal fee;
}
