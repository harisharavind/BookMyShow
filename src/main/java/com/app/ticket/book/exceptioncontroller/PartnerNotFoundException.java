package com.app.ticket.book.exceptioncontroller;

import lombok.Data;

@Data
public class PartnerNotFoundException extends RuntimeException{
    private String customErrorMessage;

    public PartnerNotFoundException(String errorMessage){
        this.customErrorMessage = errorMessage;
    }
}
