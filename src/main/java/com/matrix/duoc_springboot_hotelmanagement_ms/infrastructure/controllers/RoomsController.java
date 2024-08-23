package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.RoomsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomsController {

  private final RoomsService roomsService;

  @GetMapping()
  public ResponseEntity<List<Room>> getAllRooms(@RequestParam("limit") Optional<Integer> limit) {
    return limit.isPresent()
        ? ResponseEntity.ok(this.roomsService.getAllRooms(limit.get()))
        : ResponseEntity.ok(this.roomsService.getAllRooms());
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<Room> getRoomById(@PathVariable("roomId") Integer roomId) {
    Optional<Room> foundRoom = this.roomsService.getRoomById(roomId);
    return foundRoom.isPresent()
        ? ResponseEntity.ok(foundRoom.get())
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/availability")
  public ResponseEntity<HashMap<String, Integer>> getRoomsAvailability() {
    return ResponseEntity.ok(this.roomsService.getAvailabilityOfRooms());
  }
}
