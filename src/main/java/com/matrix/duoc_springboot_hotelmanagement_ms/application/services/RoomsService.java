package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.AvailabilityStatus;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.RoomsRepository;
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

  public List<Room> getAllRooms(Integer limit) {
    return this.roomsRepository.getRooms(limit);
  }

  public List<Room> getAllRooms() {
    return this.roomsRepository.getAllRooms();
  }

  public Optional<Room> getRoomById(int roomId) {
    return this.roomsRepository.getAllRooms().stream()
        .filter(room -> room.getRoomId() == roomId)
        .findFirst();
  }

  public HashMap<String, Integer> getAvailabilityOfRooms() {
    Number availableRooms =
        this.roomsRepository.getAllRooms().stream()
            .filter(room -> room.getAvailabilityStatus().equals(AvailabilityStatus.AVAILABLE))
            .count();
    Number occupiedRooms =
        this.roomsRepository.getAllRooms().stream()
            .filter(room -> room.getAvailabilityStatus().equals(AvailabilityStatus.UNAVAILABLE))
            .count();

    HashMap<String, Integer> availabilityOfRooms = new HashMap<>();
    availabilityOfRooms.put("Available", availableRooms.intValue());
    availabilityOfRooms.put("Not Available", occupiedRooms.intValue());

    return availabilityOfRooms;
  }
}
