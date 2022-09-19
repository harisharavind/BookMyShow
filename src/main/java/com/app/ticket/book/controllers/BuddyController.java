package com.app.ticket.book.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Optional;
import java.util.UUID;

public class BuddyController {
    Logger log = LoggerFactory.getLogger("jsonLogger");

    protected void repleteTraceId(Optional<String> traceId){
        if(traceId.isPresent()){
            MDC.put("traceId", traceId.get());
        } else {
            MDC.put("traceId", UUID.randomUUID().toString());
        }
    }
}
