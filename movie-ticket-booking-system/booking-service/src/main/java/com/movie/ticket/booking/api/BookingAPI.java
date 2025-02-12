package com.movie.ticket.booking.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.ticket.booking.exception.BookingException;
import com.movie.ticket.booking.service.BookingService;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@Slf4j
@RequiredArgsConstructor
public class BookingAPI {

    private final BookingService bookingService;
    private final Environment environment;

    @PostMapping
    public BookingDTO createBooking(@RequestBody @Valid BookingDTO bookingDTO) throws JsonProcessingException {
        log.info("Entered into BookingApi with request : {}", bookingDTO.toString());
        return this.bookingService.createBooking(bookingDTO);
    }

    @GetMapping("/tracking/{bookingId}")
    public BookingDTO getBookingDetails(@PathVariable UUID bookingId) throws BookingException {
        return this.bookingService.getBookingDetails(bookingId);
    }
}
