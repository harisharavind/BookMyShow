package com;

import com.app.ticket.book.BookMyShowApplication;
import com.app.ticket.book.repositories.PartnerRepository;
import com.app.ticket.book.repositories.document.PartnerDocument;
import com.config.MockConfig;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = BookMyShowApplication.class)
@ContextConfiguration(classes = {MockConfig.class})
@AutoConfigureMockMvc
@TestPropertySource(value = { "classpath:application-test.yml" })
public class B2BControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerRepository partnerRepository;

    @Test
    void contextLoads() {
    }

    @Value("classpath:mocks/getAllPartner.json")
    Resource getAllPartnerFile;

    @Test
    public void getAllPartnersTest() {
        try {

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                try{
                    return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException e){
                    return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
            }).registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, jsonDeserializationContext) -> {
                try{
                    return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                } catch (DateTimeParseException e){
                    return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                }
            }).create();

            List<PartnerDocument> partnerDocument = gson.fromJson(
                    new InputStreamReader(getAllPartnerFile.getInputStream()),
                    new TypeToken<List<PartnerDocument>>(){}.getType());
            Mockito.when(partnerRepository.findAll()).thenReturn(partnerDocument);

            var headers = new HttpHeaders();
            headers.add("traceId","testAllPartners");
            var entity = new HttpEntity<String>(null, headers);
            var restTemplate = new TestRestTemplate();

            var response = restTemplate.exchange("http://localhost:9090/bookmyshow/v1/b2b/partner", HttpMethod.GET, entity, Object.class);
            assertEquals(HttpStatus.OK,response.getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
