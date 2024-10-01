package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.BookingsService;
import com.matrix.duoc_springboot_hotelmanagement_ms.application.services.mappers.BookingDtoMapper;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.Booking;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.BookingStatus;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto.NewBookingDTO;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.BookingNotFoundException;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.exception.ControllerExceptionHandler;
import com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.mappers.BookingsControllerMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookingsController.class)
@Import({BookingsControllerMapper.class, ControllerExceptionHandler.class})
public class BookingsControllerTest {

  @Autowired ObjectMapper objectMapper;
  @Autowired MockMvc mockMvc;
  @Autowired BookingsControllerMapper resMapper;
  @MockBean private BookingsService bookingsService;
  @MockBean private BookingDtoMapper dtoMapper;

  @Test
  @DisplayName("[GET] BOOKING BY ID")
  void getBookingById() throws Exception {

    Booking foundBooking =
        Booking.builder()
            .bookingId(3L)
            .bookingRequestDate(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0))
            .bookingStartDate(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0))
            .bookingEndDate(LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0))
            .roomId(1L)
            .clientId(1L)
            .status(BookingStatus.APPROVED)
            .fee(new BigDecimal("19990"))
            .build();

    Mockito.when(bookingsService.getBookingById(3L)).thenReturn(foundBooking);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookings/3"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.bookingId", is(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", is("APPROVED")));

    Mockito.when(bookingsService.getBookingById(99L))
        .thenThrow(new BookingNotFoundException("Booking FAKEID not found"));
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookings/99"))
        .andExpect(MockMvcResultMatchers.status().is(404))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error_msg", is("Booking FAKEID not found")));
  }

  @Test
  @DisplayName("[POST] CREATE NEW BOOKING")
  void createNewBooking() throws Exception {

    NewBookingDTO newBooking =
        NewBookingDTO.builder()
            .bookingRequestDate(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0))
            .bookingStartDate(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0))
            .bookingEndDate(LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0))
            .roomId(1L)
            .clientId(1L)
            .fee(new BigDecimal("2000"))
            .build();
    Long newBookingId = 2L;

    String request = objectMapper.writeValueAsString(newBooking);

    Mockito.when(bookingsService.createBooking(dtoMapper.mapNewBookingDtoToDomain(newBooking)))
        .thenReturn(newBookingId);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.new_booking_id", is(2)));
  }
}
