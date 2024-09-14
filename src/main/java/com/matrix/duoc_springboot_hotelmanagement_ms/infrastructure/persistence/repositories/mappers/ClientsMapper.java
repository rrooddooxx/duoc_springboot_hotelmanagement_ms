package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientsMapper {
  public Client mapEntityToDomain(ClientEntity clientEntity) {
    return Client.builder()
        .clientId(clientEntity.getClientId())
        .clientEmail(clientEntity.getClientEmail())
        .clientLastName(clientEntity.getClientLastName())
        .clientFirstName(clientEntity.getClientFirstName())
        .clientPhone(clientEntity.getClientPhone())
        .build();
  }
}
