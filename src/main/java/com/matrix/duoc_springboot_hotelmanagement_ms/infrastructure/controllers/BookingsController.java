package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.BookingsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers.BookingDtoMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingDetail;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewBookingDTO;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.UpdateBookingStatusDto;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ServiceErrorException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.mappers.BookingsControllerMapper;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingsController {

  private final BookingsService bookingsService;
  private final BookingDtoMapper mapper;
  private final BookingsControllerMapper resMapper;

  @GetMapping()
  public ResponseEntity<CollectionModel<EntityModel<Booking>>> getAllBookings() {
    try {
      log.info("Retrieving all bookings...");
      List<Booking> serviceResponse = bookingsService.getAllBookings();

      return serviceResponse.isEmpty()
          ? ResponseEntity.noContent().build()
          : ResponseEntity.ok(
              resMapper.mapListToCollection(resMapper.mapListToEntities(serviceResponse)));
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ServiceErrorException("Error de servicio");
    }
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<EntityModel<Booking>> getBookingById(
      @PathVariable("bookingId") Long bookingId) {
    Booking foundBooking = bookingsService.getBookingById(bookingId);
    return ResponseEntity.ok(resMapper.mapDomainToEntityModel(foundBooking));
  }

  @GetMapping("/{bookingId}/detail")
  public ResponseEntity<EntityModel<BookingDetail>> getBookingDetailById(
      @PathVariable("bookingId") Long bookingId) {
    Optional<BookingDetail> foundBooking = bookingsService.getBookingDetailById(bookingId);

    return foundBooking.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(resMapper.mapDomainDetailedToEntityModel(foundBooking.get()));
  }

  @GetMapping("/{bookingId}/fee")
  public ResponseEntity<BigDecimal> getBookingStatusById(
      @PathVariable("bookingId") Long bookingId) {
    Booking foundBooking = bookingsService.getBookingById(bookingId);

    return ResponseEntity.ok(foundBooking.getFee());
  }

  @PostMapping()
  public ResponseEntity<EntityModel<Map<String, Long>>> createNewBooking(
      @Valid @RequestBody NewBookingDTO newBooking) {
    try {
      Long newBookingId =
          this.bookingsService.createBooking(this.mapper.mapNewBookingDtoToDomain(newBooking));
      return ResponseEntity.ok(resMapper.mapNewBookingToEntityModel(newBookingId));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/{bookingId}/status")
  public ResponseEntity<Object> editBookingStatus(
      @PathVariable("bookingId") Long bookingId,
      @Valid @RequestBody UpdateBookingStatusDto bookingStatusDto) {
    Booking updatedBooking =
        this.bookingsService.updateBookingStatus(bookingId, bookingStatusDto.getStatus());
    return ResponseEntity.ok(updatedBooking);
  }

  @DeleteMapping("/{bookingId}")
  public void deleteBookingById(@PathVariable("bookingId") Long bookingId) {
    this.bookingsService.deleteBookingById(bookingId);
  }
}
