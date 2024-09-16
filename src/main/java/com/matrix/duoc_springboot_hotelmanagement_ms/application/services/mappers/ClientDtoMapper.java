package com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewClientDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Component
public class ClientDtoMapper {
  public Client mapDtoToDomain(NewClientDTO newClientDTO) {
    return Client.builder()
        .clientPhone(newClientDTO.getClientPhone())
        .clientEmail(newClientDTO.getClientEmail())
        .clientFirstName(newClientDTO.getClientFirstName())
        .clientLastName(newClientDTO.getClientLastName())
        .build();
  }
}
