package com.movie.ticket.booking.handler;

import com.movie.ticket.booking.exception.BookingException;
import com.movie.ticket.booking.system.commons.common.dto.ResponseDTO;
import com.movie.ticket.booking.system.commons.common.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestControllerAdvice
@Slf4j
public class BookingHandler extends GlobalExceptionHandler {

    // To Define handlers for Custom Defined Booking Related Exceptions
    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ResponseDTO> bookingException(BookingException bookingException) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .errorDescription(bookingException.getMessage())
                        .statusCodeDescription(INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .timeStamp(LocalDateTime.now())
                        .build(),
                OK);
    }
}

