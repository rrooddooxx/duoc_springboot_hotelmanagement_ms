package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewClientDTO {

  @Size(min = 2, max = 40)
  @NotBlank
  @JsonProperty("first_name")
  private String clientFirstName;

  @NotBlank
  @JsonProperty("last_name")
  private String clientLastName;

  @Size(min = 2, max = 40)
  @NotBlank
  @Email(message = "Email inválido")
  @JsonProperty("email")
  private String clientEmail;

  @Min(100000000)
  @Max(999999999)
  @NotNull
  @JsonProperty("phone")
  private String clientPhone;
}
