package com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewBookingDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Builder
@Component
public class BookingDtoMapper {
  public Booking mapNewBookingDtoToDomain(NewBookingDTO newBookingDTO) {
    return Booking.builder()
        .clientId(newBookingDTO.getClientId())
        .roomId(newBookingDTO.getRoomId())
        .bookingRequestDate(newBookingDTO.getBookingRequestDate())
        .bookingStartDate(newBookingDTO.getBookingStartDate())
        .bookingEndDate(newBookingDTO.getBookingEndDate())
        .fee(newBookingDTO.getFee())
        .build();
  }
}
