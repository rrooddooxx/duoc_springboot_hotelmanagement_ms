package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.AvailabilityStatus;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ResourceNotFoundException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.RoomsRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.RoomEntity;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers.RoomsMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomsService {
  private final RoomsRepository roomsRepository;
  private final RoomsMapper mapper;

  public List<Room> getAllRooms(Integer limit) {
    return this.roomsRepository.findAll().stream().map(this.mapper::mapEntityToDomain).toList();
  }

  public List<Room> getAllRooms() {
    return this.roomsRepository.findAll().stream().map(this.mapper::mapEntityToDomain).toList();
  }

  public Optional<Room> getRoomById(Long roomId) {
    return this.roomsRepository.findById(roomId).map(this.mapper::mapEntityToDomain);
  }

  public HashMap<String, Integer> getAvailabilityOfRooms() {
    Number availableRooms =
        this.roomsRepository.findAll().stream()
            .map(this.mapper::mapEntityToDomain)
            .filter(room -> room.getAvailabilityStatus().equals(AvailabilityStatus.AVAILABLE))
            .count();

    Number occupiedRooms =
        this.roomsRepository.findAll().stream()
            .map(this.mapper::mapEntityToDomain)
            .filter(room -> room.getAvailabilityStatus().equals(AvailabilityStatus.UNAVAILABLE))
            .count();

    HashMap<String, Integer> availabilityOfRooms = new HashMap<>();
    availabilityOfRooms.put("Available", availableRooms.intValue());
    availabilityOfRooms.put("Not Available", occupiedRooms.intValue());

    return availabilityOfRooms;
  }

  public void updateRoomStatusById(Long roomId, AvailabilityStatus newStatus) {
    Optional<RoomEntity> room = this.roomsRepository.findById(roomId);

    if (room.isEmpty()) {
      throw new ResourceNotFoundException(String.format("Room con id (%s) no encontrada", roomId));
    }

    RoomEntity modifiedRoom = room.get();
    modifiedRoom.setAvailabilityStatus(newStatus);
    this.roomsRepository.save(modifiedRoom);
  }

  public Long createNewRoom(Room newRoom) {
    RoomEntity createdRoom = this.roomsRepository.save(this.mapper.mapDomainToEntity(newRoom));
    return createdRoom.getRoomId();
  }
}
