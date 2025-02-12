package com.movie.ticket.booking.system.commons.common.handler;


import com.movie.ticket.booking.system.commons.common.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

//        List<ObjectError> errors = methodArgumentNotValidException.getBindingResult().getAllErrors();
//        List<String> errorMessages = new ArrayList<>();
//        for (ObjectError error : errors) {
//            errorMessages.add(error.getDefaultMessage());
//        }


        return new ResponseEntity<>(ResponseDTO.builder()
                .errorDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorMessages(
                        methodArgumentNotValidException.getBindingResult().getAllErrors()
                                .stream()
                                //.map(objectError -> objectError.getDefaultMessage())
                                .map(ObjectError::getDefaultMessage)
                                .collect(Collectors.toList())
                )
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ResponseDTO> runtimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .statusCodeDescription(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .errorDescription(runtimeException.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
