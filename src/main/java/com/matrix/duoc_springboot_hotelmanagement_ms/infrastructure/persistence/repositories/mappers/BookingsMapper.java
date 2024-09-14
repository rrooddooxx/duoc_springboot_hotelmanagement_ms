package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.persistence.repositories.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingDetail;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Client;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Room;
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
}
