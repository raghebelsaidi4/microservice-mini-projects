package com.ragheb.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingAPIHandlerCallback {

    @GetMapping("/booking-fallback")
    public String bookingApiFallback() {
        return "Booking service is in maintenance mode. Please try after sometimes..... ";
    }
}
