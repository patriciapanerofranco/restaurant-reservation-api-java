package com.ppf.restaurant.dto;

import com.ppf.restaurant.entity.ReservationStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponse {

    private Long id;
    private LocalDateTime reservationTime;
    private Integer partySize;
    private ReservationStatus status;
    private Long customerId;
    private String customerName;

    public ReservationResponse(
            Long id,
            LocalDateTime reservationTime,
            Integer partySize,
            ReservationStatus status,
            Long customerId,
            String customerName
    ) {
        this.id = id;
        this.reservationTime = reservationTime;
        this.partySize = partySize;
        this.status = status;
        this.customerId = customerId;
        this.customerName = customerName;
    }

}