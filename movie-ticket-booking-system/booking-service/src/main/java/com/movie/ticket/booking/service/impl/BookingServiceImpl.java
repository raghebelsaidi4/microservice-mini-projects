package com.movie.ticket.booking.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.brokers.PaymentServiceBroker;
import com.movie.ticket.booking.entity.Booking;
import com.movie.ticket.booking.exception.BookingException;
import com.movie.ticket.booking.kafka.publisher.BooingServiceKafkaPublisher;
import com.movie.ticket.booking.system.commons.common.enums.BookingStatus;
import com.movie.ticket.booking.repository.BookingRepository;
import com.movie.ticket.booking.service.BookingService;
import com.movie.ticket.booking.system.commons.common.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BooingServiceKafkaPublisher booingServiceKafkaPublisher;
    private final PaymentServiceBroker paymentService;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) throws JsonProcessingException {
        log.info("Entered into BookingServiceImpl createBooking method with request : {} ", bookingDTO.toString());
//        Booking booking = new Booking();
//        booking.setMovieId(bookingDTO.getMovieId());
//        booking.setBookingAmount(bookingDTO.getBookingAmount());
//        booking.setBookingStatus(BookingStatus.PENDING);
//        booking.setShowDate(bookingDTO.getShowDate());
//        booking.setShowTime(bookingDTO.getShowTime());
//        booking.setSeatsSelected(bookingDTO.getSeatsSelected());

        // With Builder
        Booking booking = Booking.builder()
                .userId(bookingDTO.getUserId())
                .movieId(bookingDTO.getMovieId())
                .bookingAmount(bookingDTO.getBookingAmount())
                .bookingStatus(BookingStatus.PENDING)
                .showDate(bookingDTO.getShowDate())
                .showTime(bookingDTO.getShowTime())
                .seatsSelected(bookingDTO.getSeatsSelected())
                .build();

        this.bookingRepository.save(booking); // create a booking with booking status as PENDING
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setBookingStatus(BookingStatus.PENDING);

        // publish booking details to kafka topic(payment-request)
        booingServiceKafkaPublisher.pushBookingDetailsToPaymentRequestTopic(bookingDTO);


        //call payment service
//        log.info("Calling Stripe payment gateway to do payment for the amount {} with booking id {} ", booking.getBookingAmount(),booking.getBookingId());
//        BookingDTO bookingDTOPaymentResponse = this.paymentService.makePayment(bookingDTO);
//        log.info("Payment was successful with booking id {} ", booking.getBookingId());
//        booking.setBookingStatus(bookingDTOPaymentResponse.getBookingStatus());
//        //this.bookingRepository.save(booking);
//        return BookingDTO.builder()
//                //.bookingDTO(BookingDTO.builder()
//                .bookingId(booking.getBookingId())
//                .userId(booking.getUserId())
//                .movieId(booking.getMovieId())
//                .bookingAmount(booking.getBookingAmount())
//                .bookingStatus(bookingDTOPaymentResponse.getBookingStatus())
//                .showDate(booking.getShowDate())
//                .showTime(booking.getShowTime())
//                .seatsSelected(booking.getSeatsSelected())
//                .build();
        return bookingDTO;
    }

    @Override
    public BookingDTO getBookingDetails(UUID bookingId) {
        Booking entity = this.bookingRepository.
                findById(bookingId)
                .orElseThrow(() -> new BookingException("NO Booking details found for booking id " + bookingId));
        return BookingDTO.builder()
                .bookingId(entity.getBookingId())
                .bookingAmount(entity.getBookingAmount())
                .bookingStatus(entity.getBookingStatus())
                .movieId(entity.getMovieId())
                .showDate(entity.getShowDate())
                .showTime(entity.getShowTime())
                .userId(entity.getUserId())
                .seatsSelected(entity.getSeatsSelected())
                .build();
    }

    @Override
    @Transactional
    public void processFinalBooking(BookingDTO bookingDTO) throws JsonProcessingException {
        Booking booking = this.bookingRepository.findById(bookingDTO.getBookingId())
                .orElseThrow(() -> new BookingException("NO Booking found for booking id " + bookingDTO.getBookingId()));
        booking.setBookingStatus(bookingDTO.getBookingStatus());
    }
}
