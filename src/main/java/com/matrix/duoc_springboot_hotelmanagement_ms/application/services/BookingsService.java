package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.BookingsRepository;
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

  public List<Booking> getBookings(Integer limit) {
    return this.bookingsRepository.getBookings(limit);
  }

  public List<Booking> getAllBookings() {
    return this.bookingsRepository.getAllBookings();
  }

  public Optional<Booking> getBookingById(String bookingId) {
    return this.bookingsRepository.getAllBookings().stream()
        .filter(booking -> booking.getBookingId().equals(bookingId))
        .findFirst();
  }
}
