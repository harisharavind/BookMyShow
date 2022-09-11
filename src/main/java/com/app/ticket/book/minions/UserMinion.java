package com.app.ticket.book.minions;

import com.app.ticket.book.models.request.BookingRequest;
import com.app.ticket.book.repositories.document.UserDocument;
import org.springframework.http.ResponseEntity;

public interface UserMinion extends Minion {
    public ResponseEntity<Object> deleteBooking(String id);
    public ResponseEntity<Object> createUser(UserDocument user);
    public ResponseEntity<Object> findMoviesByCity(String city, String movie);
    public ResponseEntity<Object> bookTickets(BookingRequest request, String id);
    public ResponseEntity<Object> findAllBookings();
}
