package com.app.ticket.book.models.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookingRequest {
        //map of seat type to seat number
        private List<SeatPreference> seatPreferences;
        private LocalDate showDate;
        private LocalTime showTime;
        private String theatreName;
        private String movieName;
        private String preferredLanguage;

        @Data
        public static class SeatPreference {
                private String seatType;
                private int seatNumber;
        }
}
