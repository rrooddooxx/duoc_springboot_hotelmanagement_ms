package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.AvailabilityStatus;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookingsRepository {
  private final ArrayList<Booking> bookingsList = new ArrayList<>();
  private final ClientsRepository clientsRepository;
  private final RoomsRepository roomsRepository;

  public BookingsRepository(ClientsRepository clientsRepository, RoomsRepository roomsRepository)
      throws Exception {
    this.clientsRepository = clientsRepository;
    this.roomsRepository = roomsRepository;
    this.loadData();
  }

  private String generateBookingUuid() {
    return UUID.randomUUID().toString();
  }

  private void loadData() throws Exception {
    try {

      List<Client> clients = this.clientsRepository.getAllClients();
      List<Room> rooms =
          this.roomsRepository.getAllRooms().stream()
              .filter(room -> room.getAvailabilityStatus().equals(AvailabilityStatus.AVAILABLE))
              .toList();

      this.bookingsList.add(
          Booking.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.getFirst())
              .roomId(rooms.getFirst().getRoomId())
              .build());
      this.bookingsList.add(
          Booking.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(1))
              .roomId(rooms.get(1).getRoomId())
              .build());
      this.bookingsList.add(
          Booking.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(2))
              .roomId(rooms.get(2).getRoomId())
              .build());
      this.bookingsList.add(
          Booking.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(3))
              .roomId(rooms.get(3).getRoomId())
              .build());
      this.bookingsList.add(
          Booking.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(4))
              .roomId(rooms.get(4).getRoomId())
              .build());
      log.info("Bookings data created and loaded OK!!");
    } catch (Exception e) {
      log.error("Could not load bookings data at loadData method");
    }
  }

  public List<Booking> getBookings(Integer limit) {
    return this.bookingsList.subList(0, limit);
  }

  public List<Booking> getAllBookings() {
    System.out.println(this.bookingsList);
    return this.bookingsList;
  }
}
