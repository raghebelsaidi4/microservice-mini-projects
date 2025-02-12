package com.ragheb.notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceKafkaListener {

    private final ObjectMapper mapper;

    @KafkaListener(topics = "payment-response", groupId = "payment-response-group-2")
    public void pullBookingDetailsFromPaymentReponseTopic(String bookingJson){
        log.info("Received payment response from topic {}", bookingJson);
        try {
            // send email logic

        }catch (Exception e){
            log.error("Error while sending email for booking details {}", bookingJson);
        }
    }
}
