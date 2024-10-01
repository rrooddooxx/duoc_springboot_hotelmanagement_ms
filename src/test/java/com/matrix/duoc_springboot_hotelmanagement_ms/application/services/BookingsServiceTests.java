package com.matrix.duoc_springboot_hotelmanagement_ms.application.services;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.*;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.BookingsRepository;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.BookingEntity;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers.BookingsMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingsServiceTests {

  @Mock BookingsRepository bookingsRepository;
  @Mock ClientsService clientsService;
  @Mock RoomsService roomsService;
  @Spy BookingsMapper mapper = new BookingsMapper();

  @InjectMocks BookingsService bookingsService;

  @Test
  @DisplayName("[SUCCESS] GET BOOKING ID")
  void getBookingById() {
    Long bookindId = 1L;
    Optional<BookingEntity> foundBookingInRepo =
        Optional.of(
            BookingEntity.builder()
                .bookingId(1L)
                .clientId(123L)
                .roomId(456L)
                .status(BookingStatus.APPROVED)
                .bookingRequestDate(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0))
                .bookingStartDate(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0))
                .bookingEndDate(LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0))
                .fee(new BigDecimal("19990"))
                .build());

    Mockito.when(bookingsRepository.findById(bookindId)).thenReturn(foundBookingInRepo);
    Booking result = this.bookingsService.getBookingById(1L);

    Assertions.assertEquals(result.getBookingId(), bookindId);
    Assertions.assertEquals(result.getRoomId(), 456L);
  }

  @Test
  @DisplayName("[SUCCESS] CREATE NEW BOOKING")
  void createNewBooking() {
    Optional<Client> foundClient =
        Optional.of(
            Client.builder()
                .clientId(123L)
                .clientFirstName("John")
                .clientLastName("Doe")
                .clientEmail("johndoe@duocuc.cl")
                .clientPhone("987654321")
                .build());

    Optional<Room> foundRoom =
        Optional.of(
            Room.builder()
                .roomId(456L)
                .price(new BigDecimal("19990"))
                .bedCount(2)
                .guestCount(4)
                .availabilityStatus(AvailabilityStatus.AVAILABLE)
                .roomType(RoomType.SUITE)
                .build());

    Booking newBooking =
        Booking.builder()
            .clientId(123L)
            .roomId(456L)
            .status(BookingStatus.APPROVED)
            .bookingRequestDate(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0))
            .bookingStartDate(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0))
            .bookingEndDate(LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0))
            .fee(new BigDecimal("19990"))
            .build();

    BookingEntity mappedNewBookingBeforeDatabase =
        this.mapper.mapDomainToEntity(newBooking, foundClient.get(), foundRoom.get());

    BookingEntity bookingEntityFromDatabase =
        BookingEntity.builder()
            .bookingId(999L)
            .clientId(1L)
            .roomId(101L)
            .status(BookingStatus.APPROVED)
            .bookingRequestDate(LocalDateTime.of(2024, Month.JANUARY, 1, 12, 0))
            .bookingStartDate(LocalDateTime.of(2024, Month.JANUARY, 2, 14, 0))
            .bookingEndDate(LocalDateTime.of(2024, Month.JANUARY, 3, 10, 0))
            .fee(new BigDecimal("199.99"))
            .build();

    Mockito.when(clientsService.getClientById(123L)).thenReturn(foundClient);
    Mockito.when(roomsService.getRoomById(456L)).thenReturn(foundRoom);
    Mockito.when(bookingsRepository.save(Mockito.any(BookingEntity.class)))
        .thenReturn(bookingEntityFromDatabase);
    Long newBookingId = this.bookingsService.createBooking(newBooking);

    Assertions.assertEquals(newBookingId, 999L);
  }
}
