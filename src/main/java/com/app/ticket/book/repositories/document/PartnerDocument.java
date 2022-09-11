package com.app.ticket.book.repositories.document;

import com.redis.om.spring.annotations.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Data
@Document
@RedisHash
public class PartnerDocument implements Serializable {

    @Id
    private String id;
    @Indexed
    private String partnerOrgName;
    @Indexed
    private List<TheatreDetails> theatreDetails;
}
