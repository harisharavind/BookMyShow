package com.app.ticket.book.exceptioncontroller;

import lombok.Data;

@Data
public class BookingNotFoundException extends RuntimeException{
    private String customErrorMessage;

    public BookingNotFoundException(String errorMessage){
        this.customErrorMessage = errorMessage;
    }
}
