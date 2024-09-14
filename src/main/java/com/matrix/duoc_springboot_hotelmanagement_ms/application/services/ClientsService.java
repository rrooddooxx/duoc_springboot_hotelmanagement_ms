package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.ClientsRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers.ClientsMapper;
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
  private final ClientsMapper mapper;

  public List<Client> getClients(Integer limit) {

    return this.clientsRepository.findAll().stream().map(this.mapper::mapEntityToDomain).toList();
  }

  public List<Client> getAllClients() {
    return this.clientsRepository.findAll().stream().map(this.mapper::mapEntityToDomain).toList();
  }

  public Optional<Client> getClientById(Long clientId) {
    return this.clientsRepository.findById(clientId).map(this.mapper::mapEntityToDomain);
  }
}
