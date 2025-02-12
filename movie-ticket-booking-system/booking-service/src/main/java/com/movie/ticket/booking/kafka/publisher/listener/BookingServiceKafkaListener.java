package com.movie.ticket.booking.kafka.publisher.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.entity.Booking;
import com.movie.ticket.booking.service.BookingService;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceKafkaListener {
    // consuming data from kafka topics
    private final BookingService bookingService;
    private final ObjectMapper objectMapper;
    
    @KafkaListener(topics = "payment-response",groupId = "payment-response-group-1")
    public void pullBookingDetailsFromPaymentResponseTopic(String bookingsJson){
        log.info("Publishing booking details {} from payment response topic",bookingsJson);
        
        try {
            this.bookingService.processFinalBooking(objectMapper.readValue(bookingsJson, BookingDTO.class));
        }catch (Exception e){
            log.error("Error while processing booking {} details from payment response topic",bookingsJson);
        }
    }
}
