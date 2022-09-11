package com.app.ticket.book.repositories;

import com.app.ticket.book.repositories.document.BookingDocument;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface BookingRepository extends RedisDocumentRepository<BookingDocument, String> {

    Optional<BookingDocument> findBySeatNumberAndSeatTypeAndTheatreNameAndMovieNameAndShowDateAndShowTime(int seatNumber,
                                                                                                       String seatType,
                                                                                                       String theatreName,
                                                                                                       String movieName,
                                                                                                       LocalDate showDate,
                                                                                                       LocalTime showTime);

    Optional<BookingDocument> findByTheatreNameAndMovieName(String theatreName, String movieName);

}
