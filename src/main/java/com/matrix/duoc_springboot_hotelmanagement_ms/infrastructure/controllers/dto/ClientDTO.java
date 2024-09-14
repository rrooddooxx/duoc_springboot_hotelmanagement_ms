package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {

  @JsonProperty("client_id")
  private Integer clientId;

  @JsonProperty("first_name")
  private String clientFirstName;

  @JsonProperty("last_name")
  private String clientLastName;

  @JsonProperty("email")
  private String clientEmail;

  @JsonProperty("phone")
  private String clientPhone;
}
