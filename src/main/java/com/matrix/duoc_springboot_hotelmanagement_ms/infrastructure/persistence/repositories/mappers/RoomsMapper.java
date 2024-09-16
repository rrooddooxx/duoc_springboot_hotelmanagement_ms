package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomsMapper {
  public Room mapEntityToDomain(RoomEntity roomEntity) {
    return Room.builder()
        .roomId(roomEntity.getRoomId())
        .roomType(roomEntity.getRoomType())
        .availabilityStatus(roomEntity.getAvailabilityStatus())
        .bedCount(roomEntity.getBedCount())
        .guestCount(roomEntity.getGuestCount())
        .price(roomEntity.getPrice())
        .build();
  }

  public RoomEntity mapDomainToEntity(Room newRoom) {
    return RoomEntity.builder()
        .roomType(newRoom.getRoomType())
        .availabilityStatus(newRoom.getAvailabilityStatus())
        .bedCount(newRoom.getBedCount())
        .guestCount(newRoom.getGuestCount())
        .price(newRoom.getPrice())
        .build();
  }
}
