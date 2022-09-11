package com.app.ticket.book.minions.impl;

import com.app.ticket.book.exceptioncontroller.PartnerNotFoundException;
import com.app.ticket.book.minions.PartnerMinion;
import com.app.ticket.book.repositories.ArchievedPartnerRepository;
import com.app.ticket.book.repositories.PartnerRepository;
import com.app.ticket.book.repositories.document.ArchievedPartnerDocument;
import com.app.ticket.book.repositories.document.PartnerDocument;
import com.app.ticket.book.repositories.document.TheatreDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerMinionImpl implements PartnerMinion {

    @Autowired PartnerRepository partnerRepository;
    @Autowired ArchievedPartnerRepository archievedPartnerRepository;

    public ResponseEntity<Object> createPartner(PartnerDocument partner) {
        Optional<PartnerDocument> partnerDocument = partnerRepository.findByPartnerOrgName(partner.getPartnerOrgName());
        if(partnerDocument.isPresent()){
            partnerDocument.get().setTheatreDetails(partner.getTheatreDetails());
            partnerRepository.save(partnerDocument.get());
            return new ResponseEntity<>(partnerDocument.get().getId(), HttpStatus.OK );
        } else {
            partner = partnerRepository.save(partner);
            return new ResponseEntity<>(partner.getId(), HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> addPartnerToMovie(TheatreDetails.ScreeningMovie movie, String id) {
        Optional<PartnerDocument> partnerDocument = partnerRepository.findById(id);
        if(partnerDocument.isPresent()){
            partnerDocument.get().getTheatreDetails().forEach(
                    t -> {
                        t.getScreeningMovie().removeIf(p -> p.getMovieName().equals(movie.getMovieName()));
                        t.getScreeningMovie().add(movie);
                    }
            );
            partnerRepository.save(partnerDocument.get());
            return new ResponseEntity<>(partnerDocument.get().getId(), HttpStatus.OK );
        } else {
            throw new PartnerNotFoundException("Partner not found, for id: "+id);
        }
    }

    public ResponseEntity<Object> findAllPartner() {
        Iterable<PartnerDocument> partnerDocuments = partnerRepository.findAll();
        return new ResponseEntity<>(partnerDocuments, HttpStatus.OK);
    }

    public ResponseEntity<Object> findPartnerById(String id) {
        Optional<PartnerDocument> partnerDocument = partnerRepository.findById(id);
        if(partnerDocument.isPresent()){
            return new ResponseEntity<>(partnerDocument, HttpStatus.OK);
        } else {
            throw new PartnerNotFoundException("Partner not found, for id: "+id);
        }
    }

    public ResponseEntity<Object> deletePartnerById(String id) {
        Optional<PartnerDocument> partnerDocument = partnerRepository.findById(id);
        if(partnerDocument.isPresent()){
            ArchievedPartnerDocument archievedPartnerDocument = new ArchievedPartnerDocument();
            archievedPartnerDocument.setId(partnerDocument.get().getId());
            archievedPartnerDocument.setPartnerOrgName(partnerDocument.get().getPartnerOrgName());
            archievedPartnerDocument.setTheatreDetails(partnerDocument.get().getTheatreDetails());
            archievedPartnerRepository.save(archievedPartnerDocument);
            partnerRepository.deleteById(id);
            return new ResponseEntity<>(partnerDocument, HttpStatus.NO_CONTENT);
        } else {
            throw new PartnerNotFoundException("Partner not found, for id: "+id);
        }
    }
}
