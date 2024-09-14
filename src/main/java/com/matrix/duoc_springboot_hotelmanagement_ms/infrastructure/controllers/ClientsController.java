package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.ClientsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

  private final ClientsService clientsService;

  @GetMapping()
  public List<Client> getAllClients(@RequestParam("limit") Optional<Integer> limit) {

    return limit.isPresent()
        ? this.clientsService.getClients(limit.get())
        : this.clientsService.getAllClients();
  }

  @GetMapping("/{clientId}")
  public ResponseEntity<Client> getClientById(@PathVariable("clientId") Long clientId) {

    Optional<Client> foundClient = this.clientsService.getClientById(clientId);

    return foundClient.isPresent()
        ? ResponseEntity.ok(foundClient.get())
        : ResponseEntity.notFound().build();
  }
}
