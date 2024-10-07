package com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewRoomDTO;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper {
  public Room mapNewRoomDtoToDomain(NewRoomDTO newRoomDTO) {
    return Room.builder()
        .roomType(newRoomDTO.getRoomType())
        .availabilityStatus(newRoomDTO.getAvailabilityStatus())
        .guestCount(newRoomDTO.getGuestCount())
        .bedCount(newRoomDTO.getBedCount())
        .price(newRoomDTO.getPrice())
        .build();
  }
}
