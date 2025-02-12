package com.movie.ticket.booking.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;

import java.util.UUID;

public interface BookingService {
    public BookingDTO createBooking(BookingDTO bookingDTO) throws JsonProcessingException;
    public BookingDTO getBookingDetails(UUID bookingId);
    public void processFinalBooking(BookingDTO bookingDTO) throws JsonProcessingException;
}
