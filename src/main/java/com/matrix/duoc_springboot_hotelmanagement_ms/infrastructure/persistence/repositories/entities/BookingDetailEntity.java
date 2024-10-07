package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "booking")
@Getter
public class BookingDetailEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long bookingId;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private ClientEntity client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id")
  private RoomEntity room;

  @Column(name = "booking_status", nullable = false)
  private BookingStatus status;

  @Column(name = "date_request", nullable = false)
  private LocalDateTime bookingRequestDate;

  @Column(name = "date_start")
  private LocalDateTime bookingStartDate;

  @Column(name = "date_end")
  private LocalDateTime bookingEndDate;

  @Column(name = "fee", nullable = false)
  private BigDecimal fee;
}
