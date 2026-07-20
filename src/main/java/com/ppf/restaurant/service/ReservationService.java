package com.ppf.restaurant.service;

import com.ppf.restaurant.dto.CreateReservationRequest;
import com.ppf.restaurant.dto.ReservationResponse;
import com.ppf.restaurant.entity.Customer;
import com.ppf.restaurant.entity.Reservation;
import com.ppf.restaurant.entity.ReservationStatus;
import com.ppf.restaurant.exception.ResourceNotFoundException;
import com.ppf.restaurant.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;

    public ReservationService(
            ReservationRepository reservationRepository,
            CustomerService customerService
    ) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = getReservationEntityById(id);
        return mapToResponse(reservation);
    }

    public ReservationResponse createReservation(CreateReservationRequest request) {
        Customer customer = customerService.getCustomerEntityById(request.getCustomerId());

        Reservation reservation = new Reservation(
                request.getReservationTime(),
                request.getPartySize(),
                ReservationStatus.ACTIVE,
                customer
        );

        Reservation savedReservation = reservationRepository.save(reservation);

        return mapToResponse(savedReservation);
    }

    public ReservationResponse cancelReservation(Long id) {
        Reservation reservation = getReservationEntityById(id);

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("Reservation is already cancelled");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);

        Reservation savedReservation = reservationRepository.save(reservation);

        return mapToResponse(savedReservation);
    }

    private Reservation getReservationEntityById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    private ReservationResponse mapToResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getReservationTime(),
                reservation.getPartySize(),
                reservation.getStatus(),
                reservation.getCustomer().getId(),
                reservation.getCustomer().getName()
        );
    }
}