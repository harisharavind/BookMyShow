package com.app.ticket.book.controllers;

import com.app.ticket.book.minions.PartnerMinion;
import com.app.ticket.book.repositories.document.PartnerDocument;
import com.app.ticket.book.repositories.document.TheatreDetails;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value = "B2B")
@RequestMapping(path = "/v1/b2b", produces = MediaType.APPLICATION_JSON_VALUE)
public class B2BController extends BuddyController{

    @Autowired PartnerMinion minion;

    @Operation(summary = "Create/Update partner, theater, movie, seating details")
    @PutMapping(path = "/partner")
    public ResponseEntity<Object> createPartner(@RequestBody PartnerDocument partner,
                              @RequestHeader String traceId){
        repleteTraceId(Optional.of(traceId));
        return minion.createPartner(partner);
    }

    @Operation(summary = "update movie screening details for existing partner")
    @PutMapping(path = "/partner/{id}/movie")
    public ResponseEntity<Object> addMovie(@RequestBody TheatreDetails.ScreeningMovie movie,
                                                @PathVariable String id,
                                                @RequestHeader String traceId){
        repleteTraceId(Optional.of(traceId));
        return minion.addPartnerToMovie(movie, id);
    }

    @Operation(summary = "Get all partner information(has theatre, seating and movie details)")
    @GetMapping(path = "/partner")
    public ResponseEntity<Object> getAllPartner(@RequestHeader String traceId){
        repleteTraceId(Optional.of(traceId));
        return minion.findAllPartner();
    }

    @Operation(summary = "Get individual partner information(has theatre, seating and movie details)")
    @GetMapping(path = "/partner/{id}")
    public ResponseEntity<Object> getPartnerById(@RequestHeader String traceId,
                                                    @PathVariable() String id){
        repleteTraceId(Optional.of(traceId));
        return minion.findPartnerById(id);
    }

    @Operation(summary = "Delete a particular partner")
    @DeleteMapping(path = "/partner/{id}")
    public ResponseEntity<Object> deletePartnerById(@RequestHeader String traceId,
                                                 @PathVariable() String id){
        repleteTraceId(Optional.of(traceId));
        return minion.deletePartnerById(id);
    }

}
