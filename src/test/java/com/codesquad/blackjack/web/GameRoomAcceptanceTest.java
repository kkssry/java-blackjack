package com.codesquad.blackjack.web;

import com.codesquad.blackjack.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameRoomAcceptanceTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(GameRoomAcceptanceTest.class);

    @Autowired
    protected TestRestTemplate template;

    @Test
    public void createForm() {
        ResponseEntity<String> response = template.getForEntity("/gameRoom", String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("body : {}", response.getBody());
    }
}
