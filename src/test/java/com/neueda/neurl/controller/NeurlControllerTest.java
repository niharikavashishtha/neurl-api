package com.neueda.neurl.controller;

import com.neueda.neurl.LongURLDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local", "test", "h2"})
public class NeurlControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Order(1)
    public void test_post_short_me_url() throws Exception {

        LongURLDto longURLDto = new LongURLDto();
        longURLDto.setLongUrl("http://www.neueda.com/something/exciting/going/to/happen/with/this/long/url");

        ResponseEntity<String> returnValue = this.restTemplate.postForEntity("http://localhost:" + port + "/neurl/short-me", longURLDto, String.class);
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnValue.getBody()).isEqualTo("cvuMJB");
    }

    @Test
    @Order(2)
    public void test_get_me_url() throws Exception {

        LongURLDto longURLDto = new LongURLDto();
        longURLDto.setLongUrl("http://www.neueda.com/something/exciting/going/to/happen/with/this/long/url");

        ResponseEntity<String> returnPostValue = this.restTemplate.postForEntity("http://localhost:" + port + "/neurl/short-me", longURLDto, String.class);

        ResponseEntity<Void> returnGetValue = this.restTemplate.getForEntity("http://localhost:" + port + "/neurl/" + returnPostValue.getBody(), Void.class);
        assertThat(returnGetValue.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }
}