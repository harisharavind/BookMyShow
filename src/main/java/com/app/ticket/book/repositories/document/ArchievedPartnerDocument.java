package com.app.ticket.book.repositories.document;

import com.redis.om.spring.annotations.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@Document
@RedisHash
public class ArchievedPartnerDocument implements Serializable {

    @Id
    private String id;
    private String partnerOrgName;
    private List<TheatreDetails> theatreDetails;

}
