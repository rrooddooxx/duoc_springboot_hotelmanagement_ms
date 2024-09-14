package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingDetail;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ServiceErrorException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.BookingsDetailRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.BookingsRepository;
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
}
