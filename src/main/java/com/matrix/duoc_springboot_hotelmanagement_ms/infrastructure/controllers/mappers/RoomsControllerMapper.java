package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.RoomsController;
import java.util.HashMap;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RoomsControllerMapper {

  public EntityModel<HashMap<String, Long>> mapNewRoomToEntityModel(
      HashMap<String, Long> response) {
    return EntityModel.of(
        response,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RoomsController.class)
                    .getRoomById(response.get("new_room_id")))
            .withRel("new_room_detail"));
  }

  public EntityModel<Room> mapDomainToEntityModel(Room room) {
    return EntityModel.of(
        room,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RoomsController.class).getRoomById(room.getRoomId()))
            .withSelfRel());
  }

  public List<EntityModel<Room>> mapRoomsToEntities(List<Room> rooms) {
    return rooms.stream().map(this::mapDomainToEntityModel).toList();
  }

  public CollectionModel<EntityModel<Room>> mapToCollection(List<EntityModel<Room>> rooms) {
    return CollectionModel.of(
        rooms,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomsController.class).getAllRooms())
            .withRel("all_rooms"));
  }
}
