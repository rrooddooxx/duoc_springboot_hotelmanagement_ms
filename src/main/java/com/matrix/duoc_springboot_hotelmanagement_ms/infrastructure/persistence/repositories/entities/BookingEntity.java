package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "booking")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long bookingId;

  @NotNull(message = "Client ID cannot be empty")
  @Column(name = "client_id", nullable = false)
  private Long clientId;

  @Column(name = "room_id", nullable = false)
  private Long roomId;

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
