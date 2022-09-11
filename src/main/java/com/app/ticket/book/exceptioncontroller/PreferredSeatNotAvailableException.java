package com.app.ticket.book.exceptioncontroller;

import lombok.Data;

@Data
public class PreferredSeatNotAvailableException extends RuntimeException{
    private String customErrorMessage;

    public PreferredSeatNotAvailableException(String errorMessage){
        this.customErrorMessage = errorMessage;
    }
}
