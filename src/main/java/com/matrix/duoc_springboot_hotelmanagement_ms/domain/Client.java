package com.matrix.duoc_springboot_hotelmanagement_ms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
  private Integer clientId;
  private String clientFirstName;
  private String clientLastName;
  private String clientEmail;
  private String clientPhone;
}
