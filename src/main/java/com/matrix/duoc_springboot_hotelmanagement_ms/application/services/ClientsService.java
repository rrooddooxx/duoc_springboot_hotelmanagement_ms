package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.ClientsRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.ClientEntity;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers.ClientsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Example;
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

  public Long createNewClient(Client newClient) throws BadRequestException {
    boolean clientExits =
        this.clientsRepository.exists(
            Example.of(ClientEntity.builder().clientEmail(newClient.getClientEmail()).build()));

    if (clientExits) {
      log.error("Error! Client already exists with email {}", newClient.getClientEmail());
      throw new BadRequestException("Cliente con ese correo ya existe en sistema");
    }

    ClientEntity createdClient =
        this.clientsRepository.save(this.mapper.mapEntityToDomain(newClient));
    return createdClient.getClientId();
  }
}
