package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.ClientsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers.ClientDtoMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewClientDTO;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewClientResponseDTO;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.mappers.ClientsControllerMapper;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

  private final ClientsService clientsService;
  private final ClientDtoMapper mapper;
  private final ClientsControllerMapper resMapper;

  @GetMapping()
  public ResponseEntity<CollectionModel<EntityModel<Client>>> getAllClients() {

    var serviceResponse = this.clientsService.getAllClients();
    return ResponseEntity.ok(
        resMapper.mapToCollection(resMapper.mapClientsToEntities(serviceResponse)));
  }

  @GetMapping("/{clientId}")
  public ResponseEntity<Client> getClientById(@PathVariable("clientId") Long clientId) {

    Optional<Client> foundClient = this.clientsService.getClientById(clientId);

    return foundClient.isPresent()
        ? ResponseEntity.ok(foundClient.get())
        : ResponseEntity.notFound().build();
  }

  @PostMapping()
  public ResponseEntity<NewClientResponseDTO> createNewClient(
      @Valid @RequestBody NewClientDTO newClient) {
    try {
      Long newClientId = this.clientsService.createNewClient(this.mapper.mapDtoToDomain(newClient));
      return ResponseEntity.ok(
          NewClientResponseDTO.builder().clientId(newClientId).status("Success").build());
    } catch (Exception e) {
      if (e instanceof BadRequestException) {
        log.error(e.getMessage());
        ResponseEntity.of(Optional.of(e.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      return ResponseEntity.internalServerError().build();
    }
  }
}
