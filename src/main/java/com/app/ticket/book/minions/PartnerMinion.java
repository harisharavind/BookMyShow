package com.app.ticket.book.minions;

import com.app.ticket.book.repositories.document.PartnerDocument;
import com.app.ticket.book.repositories.document.TheatreDetails;
import org.springframework.http.ResponseEntity;

public interface PartnerMinion {
    public ResponseEntity<Object> createPartner(PartnerDocument partner);
    public ResponseEntity<Object> addPartnerToMovie(TheatreDetails.ScreeningMovie movie, String id);
    public ResponseEntity<Object> findAllPartner();
    public ResponseEntity<Object> findPartnerById(String id);
    public ResponseEntity<Object> deletePartnerById(String id);
}
