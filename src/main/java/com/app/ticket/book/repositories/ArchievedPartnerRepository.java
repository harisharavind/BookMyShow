package com.app.ticket.book.repositories;

import com.app.ticket.book.repositories.document.ArchievedPartnerDocument;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchievedPartnerRepository extends RedisDocumentRepository<ArchievedPartnerDocument, String> {

    Optional<ArchievedPartnerDocument> findByPartnerOrgName(String partnerOrgName);

}
