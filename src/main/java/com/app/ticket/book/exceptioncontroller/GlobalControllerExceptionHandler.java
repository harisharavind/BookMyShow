package com.app.ticket.book.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> bookingNotFoundException(BookingNotFoundException ex) {
        return new ResponseEntity<>(ex.getCustomErrorMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PreferredSeatNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> preferredSeatNotAvailableException(PreferredSeatNotAvailableException ex) {
        return new ResponseEntity<>(ex.getCustomErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> globalCast(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PartnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> partnerNotFoundException(PartnerNotFoundException ex) {
        return new ResponseEntity<>(ex.getCustomErrorMessage(), HttpStatus.NOT_FOUND);
    }
}
