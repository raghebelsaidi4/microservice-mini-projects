package com.movie.ticket.booking.kafka.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooingServiceKafkaPublisher {
    // write code to publish message to kafka topic
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void pushBookingDetailsToPaymentRequestTopic(BookingDTO bookingDTO) {
        log.info("Publishing booking details to payment request topic");
        try {
            this.kafkaTemplate.send("payment-request", objectMapper.writeValueAsString(bookingDTO));
        }catch (Exception e) {
            log.error("Error by publish the data to payment-request topic");
        }
    }
}
