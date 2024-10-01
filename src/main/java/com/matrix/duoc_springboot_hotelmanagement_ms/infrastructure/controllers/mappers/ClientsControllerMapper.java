package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.ClientsController;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClientsControllerMapper {
  public EntityModel<Client> mapDomainToEntityModel(Client client) {
    return EntityModel.of(
        client,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClientsController.class)
                    .getClientById(client.getClientId()))
            .withSelfRel());
  }

  public List<EntityModel<Client>> mapClientsToEntities(List<Client> clients) {
    return clients.stream().map(this::mapDomainToEntityModel).toList();
  }

  public CollectionModel<EntityModel<Client>> mapToCollection(List<EntityModel<Client>> clients) {
    return CollectionModel.of(
        clients,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClientsController.class).getAllClients())
            .withRel("all_clients"));
  }
}
