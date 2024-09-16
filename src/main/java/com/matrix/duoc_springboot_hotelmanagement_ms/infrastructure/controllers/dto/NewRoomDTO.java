package com.matrix.duoc_springboot_hotelmanagement_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.AvailabilityStatus;
import com.matrix.duoc_springboot_hotelmanagement_ms.domain.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class NewRoomDTO {
    @JsonProperty("room_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoomType roomType;

    @JsonProperty("availability")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AvailabilityStatus availabilityStatus;

    @JsonProperty("guest_count")
    @NotNull
    @Min(1)
    private int guestCount;

    @JsonProperty("bed_count")
    @NotNull
    @Min(1)
    private int bedCount;

    @JsonProperty("price")
    @Min(1000)
    private BigDecimal price;

}
