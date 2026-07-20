package com.ppf.restaurant.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateReservationRequest {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "Reservation time is required")
    @Future(message = "Reservation time must be in the future")
    private LocalDateTime reservationTime;

    @NotNull(message = "Party size is required")
    @Positive(message = "Party size must be greater than zero")
    private Integer partySize;
}