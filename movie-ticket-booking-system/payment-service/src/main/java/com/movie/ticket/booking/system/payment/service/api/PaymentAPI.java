package com.movie.ticket.booking.system.payment.service.api;

import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import com.movie.ticket.booking.system.payment.service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentAPI {

    private final PaymentService paymentService;

    @PostMapping
    public void makePayment(@RequestBody @Valid BookingDTO bookingDTO) {
        log.info("Entered into paymentApi with the request {}", bookingDTO.toString());
        this.paymentService.makePayment(bookingDTO);

    }
}
