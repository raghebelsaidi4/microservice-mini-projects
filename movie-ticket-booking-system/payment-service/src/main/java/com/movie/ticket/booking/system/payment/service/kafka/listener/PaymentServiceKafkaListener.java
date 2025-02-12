package com.movie.ticket.booking.system.payment.service.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import com.movie.ticket.booking.system.payment.service.entity.Payment;
import com.movie.ticket.booking.system.payment.service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceKafkaListener {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @KafkaListener(topics = "payment-request", groupId = "payment-request-group-1")
    public void receieveDataFromPaymentRequestTopic(String bookingDetailsJson){
        log.info("Received booking details from payment request: {}", bookingDetailsJson);
        try {
            this.paymentService.makePayment(objectMapper.readValue(bookingDetailsJson, BookingDTO.class));
        }catch (Exception e){
            log.error("Error while receiving booking details {} from payment request", bookingDetailsJson);
        }
    }
}
