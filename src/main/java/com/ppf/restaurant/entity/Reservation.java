package com.ppf.restaurant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private LocalDateTime reservationTime;

    @Setter
    private Integer partySize;

    @Setter
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Reservation() {
    }

    public Reservation(LocalDateTime reservationTime, Integer partySize, ReservationStatus status, Customer customer) {
        this.reservationTime = reservationTime;
        this.partySize = partySize;
        this.status = status;
        this.customer = customer;
    }
}
