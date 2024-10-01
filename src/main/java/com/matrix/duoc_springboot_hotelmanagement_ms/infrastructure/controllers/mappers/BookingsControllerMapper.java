package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.mappers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingDetail;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.BookingsController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class BookingsControllerMapper {

  public EntityModel<Map<String, Long>> mapNewBookingToEntityModel(Long bookingId) {
    Map<String, Long> response =
        new HashMap<>() {
          {
            put("new_booking_id", bookingId);
          }
        };
    return EntityModel.of(
        response,
        linkTo(methodOn(BookingsController.class).getBookingById(bookingId))
            .withRel("new_booking_detail"));
  }

  public EntityModel<Booking> mapDomainToEntityModel(Booking booking) {
    return EntityModel.of(
        booking,
        linkTo(methodOn(BookingsController.class).getBookingById(booking.getBookingId()))
            .withSelfRel());
  }

  public EntityModel<BookingDetail> mapDomainDetailedToEntityModel(BookingDetail booking) {
    return EntityModel.of(
        booking,
        linkTo(methodOn(BookingsController.class).getBookingById(booking.getBookingId()))
            .withSelfRel());
  }

  public List<EntityModel<Booking>> mapListToEntities(List<Booking> bookings) {
    return bookings.stream().map(this::mapDomainToEntityModel).toList();
  }

  public CollectionModel<EntityModel<Booking>> mapListToCollection(
      List<EntityModel<Booking>> bookings) {
    return CollectionModel.of(
        bookings,
        linkTo(methodOn(BookingsController.class).getAllBookings()).withRel("all-bookings"));
  }
}
