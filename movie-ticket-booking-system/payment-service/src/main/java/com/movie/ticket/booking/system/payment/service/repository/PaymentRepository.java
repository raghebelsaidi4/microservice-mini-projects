package com.movie.ticket.booking.system.payment.service.repository;

import com.movie.ticket.booking.system.payment.service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
