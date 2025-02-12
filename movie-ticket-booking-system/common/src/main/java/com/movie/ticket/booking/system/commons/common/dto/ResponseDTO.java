package com.movie.ticket.booking.system.commons.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    //private String developerMessage;
    private List<String> errorMessages;
    private String errorDescription;
    private BookingDTO bookingDTO;
    private String statusCodeDescription;
    private LocalDateTime timeStamp;
}
