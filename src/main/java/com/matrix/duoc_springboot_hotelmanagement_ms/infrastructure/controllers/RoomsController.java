package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.RoomsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers.RoomDtoMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewRoomDTO;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.UpdateRoomDto;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
@Slf4j
public class RoomsController {

  private final RoomsService roomsService;
  private final RoomDtoMapper mapper;

  @GetMapping()
  public ResponseEntity<List<Room>> getAllRooms(@RequestParam("limit") Optional<Integer> limit) {
    return limit.isPresent()
        ? ResponseEntity.ok(this.roomsService.getAllRooms(limit.get()))
        : ResponseEntity.ok(this.roomsService.getAllRooms());
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<Room> getRoomById(@PathVariable("roomId") Long roomId) {
    Optional<Room> foundRoom = this.roomsService.getRoomById(roomId);
    return foundRoom.isPresent()
        ? ResponseEntity.ok(foundRoom.get())
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/availability")
  public ResponseEntity<HashMap<String, Integer>> getRoomsAvailability() {
    return ResponseEntity.ok(this.roomsService.getAvailabilityOfRooms());
  }

  @PostMapping()
  public ResponseEntity<HashMap<String, Long>> createRoom(@RequestBody NewRoomDTO newRoom) {
    Long newRoomId = this.roomsService.createNewRoom(this.mapper.mapNewRoomDtoToDomain(newRoom));
    HashMap<String, Long> response = new HashMap<>();
    response.put("new_room_id", newRoomId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{roomId}")
  public ResponseEntity<Object> updateRoomIdStatus(
      @PathVariable("roomId") Long roomId, @Valid @RequestBody UpdateRoomDto updateRoomDto) {
    log.info(roomId.toString());
    log.info(String.valueOf(updateRoomDto.getAvailabilityStatus()));
    this.roomsService.updateRoomStatusById(roomId, updateRoomDto.getAvailabilityStatus());

    return ResponseEntity.ok(
        UpdateRoomDto.builder()
            .statusMsg(String.format("Habitación con id %s modificada con " + "éxito.", roomId))
            .build());
  }
}
