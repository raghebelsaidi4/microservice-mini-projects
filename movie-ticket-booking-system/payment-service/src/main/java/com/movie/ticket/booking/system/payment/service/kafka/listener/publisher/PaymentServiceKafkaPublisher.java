package com.movie.ticket.booking.system.payment.service.kafka.listener.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceKafkaPublisher {
    private final ObjectMapper objectMapper;
    private final PaymentServiceKafkaPublisher kafkaPublisher;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void pushBookingDetailsToPaymentResponseTopic(BookingDTO bookingDTO) {
        log.info("Pushing Booking details {} to Payment Response Topic", bookingDTO.toString());

        try {
            this.kafkaTemplate.send("payment-response", objectMapper.writeValueAsString(bookingDTO));

        }catch (Exception e) {
            log.error("Error while pushing Booking details to Payment Response Topic", e);
        }
    }
}
