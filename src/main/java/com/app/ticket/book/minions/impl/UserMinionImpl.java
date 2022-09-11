package com.app.ticket.book.minions.impl;

import com.app.ticket.book.exceptioncontroller.BookingNotFoundException;
import com.app.ticket.book.exceptioncontroller.PreferredSeatNotAvailableException;
import com.app.ticket.book.minions.UserMinion;
import com.app.ticket.book.models.request.BookingRequest;
import com.app.ticket.book.repositories.BookingRepository;
import com.app.ticket.book.repositories.PartnerRepository;
import com.app.ticket.book.repositories.UserRepository;
import com.app.ticket.book.repositories.document.BookingDocument;
import com.app.ticket.book.repositories.document.PartnerDocument;
import com.app.ticket.book.repositories.document.UserDocument;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMinionImpl implements UserMinion {

    @Autowired UserRepository userRepository;
    @Autowired PartnerRepository partnerRepository;
    @Autowired BookingRepository bookingRepository;

    public ResponseEntity<Object> deleteBooking(String id) {
        Optional<BookingDocument> bookingDocument = bookingRepository.findById(id);
        if(bookingDocument.isPresent()) {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            throw new BookingNotFoundException("Booking for ID: "+ id +" not found");
        }
    }

    public ResponseEntity<Object> createUser(UserDocument user) {
        Optional<UserDocument> userDocument = userRepository.findByUsername(user.getUsername());
        if (userDocument.isPresent()) {
            userDocument.get().setEmail(user.getEmail());
            userDocument.get().setPhoneNumber(user.getPhoneNumber());
            userDocument.get().setPreferredLanguages(user.getPreferredLanguages());
            userRepository.save(userDocument.get());
            return new ResponseEntity<>(userDocument.get().getId(), HttpStatus.OK);
        } else {
            user = userRepository.save(user);
            return new ResponseEntity<>(user.getId(), HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> findMoviesByCity(String city, String movie) {
        if(StringUtils.isBlank(city))
            return new ResponseEntity<>("city is invalid",HttpStatus.BAD_REQUEST);

        Optional<List<PartnerDocument>> partnerDocumentList;
        if(StringUtils.isNotBlank(movie)) {
            partnerDocumentList = partnerRepository.findByTheatreDetails_CityAndTheatreDetails_ScreeningMovie_MovieName(city, movie);
        } else {
            partnerDocumentList = partnerRepository.findByTheatreDetails_City(city);
        }

        return new ResponseEntity<>(partnerDocumentList.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> bookTickets(BookingRequest request, String id) {
        request.getSeatPreferences().stream().forEach(
                s-> {
                    Optional<BookingDocument> bookingDocumentCheck = bookingRepository.findBySeatNumberAndSeatTypeAndTheatreNameAndMovieNameAndShowDateAndShowTime(
                            s.getSeatNumber(),
                            s.getSeatType(),
                            request.getTheatreName(),
                            request.getMovieName(),
                            request.getShowDate(),
                            request.getShowTime());
                    log.info("bookingDocumentCheck.isPresent() : "+bookingDocumentCheck.isPresent());
                    if(bookingDocumentCheck.isPresent())
                        throw new PreferredSeatNotAvailableException("seat not available "+
                                s.getSeatNumber()+" , "+s.getSeatType()+" , "+ request.getTheatreName()+" , "
                                + request.getMovieName()+" , "+ request.getShowDate()+" , "+
                                request.getShowTime());
                }
        );
        List<BookingDocument> bookingDocuments = new ArrayList<>();
        request.getSeatPreferences().stream().forEach(
                r -> {
                    BookingDocument document = new BookingDocument();
                    document.setUserId(id);
                    document.setSeatType(r.getSeatType());
                    document.setSeatNumber(r.getSeatNumber());
                    document.setTheatreName(request.getTheatreName());
                    document.setMovieName(request.getMovieName());
                    document.setLanguage(request.getPreferredLanguage());
                    document.setShowDate(request.getShowDate());
                    document.setShowTime(request.getShowTime());
                    bookingDocuments.add(bookingRepository.save(document));
                }
        );

        return new ResponseEntity<>(bookingDocuments, HttpStatus.OK);
    }

    public ResponseEntity<Object> findAllBookings() {
        Iterable<BookingDocument> bookingDocuments = bookingRepository.findAll();

        return new ResponseEntity<>(bookingDocuments, HttpStatus.OK);
    }
}
