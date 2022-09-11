package com.app.ticket.book.controllers;

import com.app.ticket.book.minions.UserMinion;
import com.app.ticket.book.models.request.BookingRequest;
import com.app.ticket.book.repositories.document.UserDocument;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value = "B2C")
@RequestMapping(path = "/v1/b2c", produces = MediaType.APPLICATION_JSON_VALUE)
public class B2CController extends BuddyController {

    @Autowired UserMinion minion;

    @Operation(summary = "Create/update end user information")
    @PutMapping(path = "/user")
    public ResponseEntity<Object> createUser(@RequestBody UserDocument user,
                                             @RequestHeader String traceId) {
        repleteTraceId(Optional.of(traceId));
        return minion.createUser(user);
    }


    @Operation(summary = "Search for available movies in your city")
    @GetMapping(path = "/search")
    public ResponseEntity<Object> getMoviesByCity(@RequestParam String city,
                                                  @RequestParam(required = false) String movie,
                                                  @RequestHeader String traceId) {
        repleteTraceId(Optional.of(traceId));
        return minion.findMoviesByCity(city, movie);
    }

    @Operation(summary = "Create new booking, including bulk bookings")
    @PostMapping(path = "/user/{id}/book")
    public ResponseEntity<Object> bookTicket(@RequestBody BookingRequest request,
                                             @RequestHeader String traceId,
                                             @PathVariable String id) {
        repleteTraceId(Optional.of(traceId));
        return minion.bookTickets(request, id);
    }

    @Operation(summary = "Get all bookings")
    @GetMapping(path = "/bookings")
    public ResponseEntity<Object> getAllBookings(@RequestHeader String traceId) {
        repleteTraceId(Optional.of(traceId));
        return minion.findAllBookings();
    }

    @Operation(summary = "Delete particular booking by booking id")
    @DeleteMapping(path = "/booking/{id}")
    public ResponseEntity<Object> deleteBooking(@RequestHeader String traceId,
                                             @PathVariable String id) {
        repleteTraceId(Optional.of(traceId));
        return minion.deleteBooking(id);
    }

}
