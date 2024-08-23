package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.ClientsRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientsService {
  private final ClientsRepository clientsRepository;

  public List<Client> getClients(Integer limit) {
    return this.clientsRepository.getClients(limit);
  }

  public List<Client> getAllClients() {
    return this.clientsRepository.getAllClients();
  }

  public Optional<Client> getClientById(Integer clientId) {
    return this.clientsRepository.getAllClients().stream()
        .filter(client -> client.getClientId().equals(clientId))
        .findFirst();
  }
}
