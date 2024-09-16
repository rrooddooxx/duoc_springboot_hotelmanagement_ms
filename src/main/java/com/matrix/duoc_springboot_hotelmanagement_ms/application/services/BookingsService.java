package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.*;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.BookingNotFoundException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ResourceNotFoundException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.RoomNotAvailableException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ServiceErrorException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.BookingsDetailRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.BookingsRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.BookingEntity;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers.BookingsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingsService {
  private final BookingsRepository bookingsRepository;
  private final ClientsService clientsService;
  private final RoomsService roomsService;
  private final BookingsDetailRepository bookingsDetailRepository;
  private final BookingsMapper mapper;

  public List<Booking> getBookings(Integer limit) {
    return this.bookingsRepository.findAll().stream().map(this.mapper::mapEntityToDomain).toList();
  }

  public List<Booking> getAllBookings() {
    log.info("Querying all bookings from the database...");
    return this.bookingsRepository.findAll().stream().map(this.mapper::mapEntityToDomain).toList();
  }

  public Optional<Booking> getBookingById(Long bookingId) {
    try {
      return this.bookingsRepository.findById(bookingId).map(this.mapper::mapEntityToDomain);
    } catch (Exception e) {
      throw new ServiceErrorException("Error general del servicio. Reintente.");
    }
  }

  public Optional<BookingDetail> getBookingDetailById(Long bookingId) {
    try {
      return this.bookingsDetailRepository
          .findById(bookingId)
          .map(this.mapper::mapEntitiesToDomain);
    } catch (Exception e) {
      throw new ServiceErrorException("Error general del servicio. Reintente.");
    }
  }

  public Long createBooking(Booking newBooking) {
    try {
      Optional<Client> foundClient = this.clientsService.getClientById(newBooking.getClientId());
      Optional<Room> foundRoom = this.roomsService.getRoomById(newBooking.getRoomId());

      if (foundClient.isEmpty() || foundRoom.isEmpty()) {
        throw new ResourceNotFoundException(
            "No se ha encontrado cliente ó habitación válida para la "
                + "solicitud, comprueba los datos de entrada.");
      }

      if (foundRoom.get().getAvailabilityStatus().equals(AvailabilityStatus.UNAVAILABLE)) {
        throw new RoomNotAvailableException(
            String.format(
                "Habitación con ID (%s) ya está ocupada | %s",
                newBooking.getRoomId(), AvailabilityStatus.UNAVAILABLE));
      }

      BookingEntity createdBooking =
          this.bookingsRepository.save(
              this.mapper.mapDomainToEntity(newBooking, foundClient.get(), foundRoom.get()));

      return createdBooking.getBookingId();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Booking updateBookingStatus(Long bookingId, BookingStatus newStatus) {
    Optional<BookingEntity> booking = this.bookingsRepository.findById(bookingId);
    if (booking.isEmpty()) {
      throw new BookingNotFoundException(
          String.format(
              "Booking con Id (%s) no ha sido encontrado" + ". Abortando operación", bookingId));
    }

    log.info("Updating booking with id ({}) to new status ({})", bookingId, newStatus);
    BookingEntity updatedBooking = booking.get();
    updatedBooking.setStatus(newStatus);

    return this.mapper.mapEntityToDomain(this.bookingsRepository.save(updatedBooking));
  }

  public void deleteBookingById(Long bookingId) {
    boolean bookingExists = this.bookingsRepository.existsById(bookingId);
    if (!bookingExists) {
      throw new ResourceNotFoundException(
          String.format("No existe reserva con id %s en el " + "sistema.", bookingId));
    }
    this.bookingsRepository.deleteById(bookingId);
  }
}
