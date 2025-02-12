package com.movie.ticket.booking.system.commons.common.dto;

import com.movie.ticket.booking.system.commons.common.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
public class BookingDTO {

    private UUID bookingId;

    @NotBlank(message = "user id is a mandatory")
    private String userId;

    @Positive(message = "Please provide a valid movie id")
    @NotNull(message = "movie id is a mandatory")
    private Integer movieId;

    @NotNull(message = "You need to select at least one seat to create a booking")
    private List<String> seatsSelected;

    @NotNull(message = "Select the show date")
    private LocalDate showDate;

    @NotNull(message = "Select the show time")
    private LocalTime showTime;

    @Positive(message = "Booking amount must be a positive value")
    @NotNull(message = "booking amount is a mandatory")
    private Double bookingAmount;

    private BookingStatus bookingStatus;
}
