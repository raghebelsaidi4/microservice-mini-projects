package com.movie.ticket.booking.brokers;

import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentServiceBroker {

    @PostMapping("payments")
    public BookingDTO makePayment(@RequestBody BookingDTO bookingDTO);
}
