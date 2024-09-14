package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.BookingsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingDetail;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ServiceErrorException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingsController {

  private final BookingsService bookingsService;

  @GetMapping()
  public ResponseEntity<List<Booking>> getAllBookings(
      @RequestParam(required = false) Optional<Integer> limit) {
    try {
      log.info("Retrieving all bookings...");
      List<Booking> serviceResponse =
          limit.isPresent()
              ? bookingsService.getBookings(limit.get())
              : bookingsService.getAllBookings();

      return serviceResponse.isEmpty()
          ? ResponseEntity.noContent().build()
          : ResponseEntity.ok(serviceResponse);
    } catch (Exception e) {
      throw new ServiceErrorException("Error de servicio");
    }
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") Long bookingId) {
    Optional<Booking> foundBooking = bookingsService.getBookingById(bookingId);

    return foundBooking.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(foundBooking.get());
  }

  @GetMapping("/{bookingId}/detail")
  public ResponseEntity<BookingDetail> getBookingDetailById(
      @PathVariable("bookingId") Long bookingId) {
    Optional<BookingDetail> foundBooking = bookingsService.getBookingDetailById(bookingId);

    return foundBooking.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(foundBooking.get());
  }

  @GetMapping("/{bookingId}/fee")
  public ResponseEntity<BigDecimal> getBookingStatusById(
      @PathVariable("bookingId") Long bookingId) {
    Optional<Booking> foundBooking = bookingsService.getBookingById(bookingId);

    return foundBooking.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(foundBooking.get().getFee());
  }
}
