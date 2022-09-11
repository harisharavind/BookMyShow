package com.app.ticket.book.repositories.document;

import com.redis.om.spring.annotations.Document;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Document
@RedisHash
public class TheatreDetails implements Serializable {

    @Indexed
    private String theatreName;
    @Indexed
    private String city;
    @Indexed
    private List<ScreeningMovie> screeningMovie;
    private SeatingCategory seatingCategory;

    @Data
    public static class ScreeningMovie {
        private LocalDate screeningStartDate;
        private LocalDate screeningEndDate;
        @Indexed
        private Set<String> languages;
        @Indexed
        private String movieName;
        @Indexed
        private Set<LocalTime> showTimings;
    }

    @Data
    public static class SeatingCategory {
        private Map<String, SeatingDetails> seatingCategoryMap;
    }

    @Data
    public static class SeatingDetails {
        private int numberOfSeats;
        private int price;
    }
}
