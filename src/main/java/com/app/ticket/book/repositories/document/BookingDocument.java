package com.app.ticket.book.repositories.document;

import com.redis.om.spring.annotations.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
@Data
@RedisHash
public class BookingDocument {
    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private LocalDate showDate;
    @Indexed
    private LocalTime showTime;
    @Indexed
    private String theatreName;
    @Indexed
    private String movieName;
    private String language;
    @Indexed
    private int seatNumber;
    @Indexed
    private String seatType;
}
