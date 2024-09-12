package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer clientId;

  @Column(name = "first_name", nullable = false)
  private String clientFirstName;

  @Column(name = "last_name", nullable = false)
  private String clientLastName;

  @Column(name = "email", nullable = false)
  private String clientEmail;

  @Column(name = "phone", nullable = false)
  private String clientPhone;
}
