package com.app.ticket.book.repositories;

import com.app.ticket.book.repositories.document.UserDocument;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends RedisDocumentRepository<UserDocument, String> {

    Optional<UserDocument> findByUsername(String username);

}
