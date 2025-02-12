package com.movie.ticket.booking.system.payment.service.service;

import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;

public interface PaymentService{
    public void makePayment(BookingDTO bookingDTO);
}
