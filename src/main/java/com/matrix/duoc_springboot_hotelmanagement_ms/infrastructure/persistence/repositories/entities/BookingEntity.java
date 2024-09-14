package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long bookingId;

  @NotBlank(message = "Client ID cannot be empty")
  @Column(name = "client_id", nullable = false)
  private Long clientId;

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
