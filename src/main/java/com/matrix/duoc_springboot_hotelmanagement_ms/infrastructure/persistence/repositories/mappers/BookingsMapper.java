package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.*;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.BookingDetailEntity;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.entities.BookingEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingsMapper {
  public Booking mapEntityToDomain(BookingEntity booking) {
    return Booking.builder()
        .bookingId(booking.getBookingId())
        .roomId(booking.getRoomId())
        .clientId(booking.getClientId())
        .status(booking.getStatus())
        .bookingRequestDate(booking.getBookingRequestDate())
        .bookingStartDate(booking.getBookingStartDate())
        .bookingEndDate(booking.getBookingEndDate())
        .fee(booking.getFee())
        .build();
  }

  public BookingDetail mapEntitiesToDomain(BookingDetailEntity bookingDetailEntity) {
    var room = bookingDetailEntity.getRoom();
    var client = bookingDetailEntity.getClient();
    return BookingDetail.builder()
        .bookingId(bookingDetailEntity.getBookingId())
        .room(
            Room.builder()
                .roomId(room.getRoomId())
                .roomType(room.getRoomType())
                .guestCount(room.getGuestCount())
                .bedCount(room.getBedCount())
                .availabilityStatus(room.getAvailabilityStatus())
                .price(room.getPrice())
                .build())
        .status(bookingDetailEntity.getStatus())
        .bookingRequestDate(bookingDetailEntity.getBookingRequestDate())
        .bookingStartDate(bookingDetailEntity.getBookingStartDate())
        .bookingEndDate(bookingDetailEntity.getBookingEndDate())
        .client(
            Client.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientEmail(client.getClientEmail())
                .clientPhone(client.getClientPhone())
                .build())
        .fee(bookingDetailEntity.getFee())
        .build();
  }

  public BookingEntity mapDomainToEntity(Booking newBooking, Client client, Room room) {
    return BookingEntity.builder()
        .clientId(client.getClientId())
        .roomId(room.getRoomId())
        .status(BookingStatus.PENDING)
        .bookingRequestDate(newBooking.getBookingRequestDate())
        .bookingStartDate(newBooking.getBookingStartDate())
        .bookingEndDate(newBooking.getBookingEndDate())
        .fee(newBooking.getFee())
        .build();
  }
}
