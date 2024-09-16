package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long clientId;

  @Column(name = "first_name", nullable = false)
  private String clientFirstName;

  @Column(name = "last_name", nullable = false)
  private String clientLastName;

  @Column(name = "email", nullable = false)
  private String clientEmail;

  @Column(name = "phone", nullable = false)
  private String clientPhone;
}
