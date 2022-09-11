package com.app.ticket.book.repositories;

import com.app.ticket.book.repositories.document.PartnerDocument;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends RedisDocumentRepository<PartnerDocument, String> {

    Optional<PartnerDocument> findByPartnerOrgName(String partnerOrgName);

    Optional<List<PartnerDocument>> findByTheatreDetails_City(String city);

    Optional<List<PartnerDocument>> findByTheatreDetails_CityAndTheatreDetails_ScreeningMovie_MovieName(String city, String movieName);

}
