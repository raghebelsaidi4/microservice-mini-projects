package com.movie.ticket.booking.system.payment.service.service.impl;

import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import com.movie.ticket.booking.system.commons.common.enums.BookingStatus;
import com.movie.ticket.booking.system.payment.service.entity.Payment;
import com.movie.ticket.booking.system.payment.service.enums.PaymentStatus;
import com.movie.ticket.booking.system.payment.service.kafka.listener.publisher.PaymentServiceKafkaPublisher;
import com.movie.ticket.booking.system.payment.service.repository.PaymentRepository;
import com.movie.ticket.booking.system.payment.service.service.PaymentService;
import com.movie.ticket.booking.system.payment.service.service.StripeApiPaymentGateway;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StripeApiPaymentGateway stripeApiPaymentGateway;
    private final PaymentServiceKafkaPublisher paymentServiceKafkaPublisher;

    @Override
    @Transactional(rollbackFor = StripeException.class)
    public void makePayment(BookingDTO bookingDTO) {
        log.info("Entered into PaymentServiceImpl with request {} ", bookingDTO.toString());
        Payment payment = Payment.builder()
                .bookingId(bookingDTO.getBookingId())
                .paymentStatus(PaymentStatus.PENDING)
                .paymentAmount(bookingDTO.getBookingAmount())
                .build();
        this.paymentRepository.save(payment);
        bookingDTO = this.stripeApiPaymentGateway.processPayment(bookingDTO);
        //payment.setPaymentTimeStamp(LocalDateTime.now());
        if (bookingDTO.getBookingStatus().equals(BookingStatus.CONFIRMED)) {
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            payment.setPaymentDateTime(LocalDateTime.now());
            this.paymentRepository.save(payment);
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setPaymentDateTime(LocalDateTime.now());
        }
        this.paymentServiceKafkaPublisher.pushBookingDetailsToPaymentResponseTopic(bookingDTO);
    }
}
