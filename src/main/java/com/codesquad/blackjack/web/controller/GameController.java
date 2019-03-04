package com.codesquad.blackjack.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    private static final Logger log = LogManager.getLogger(GameController.class);

    @GetMapping("/game")
    public String create() {
        log.info("adfsa");
        return "aaa";
    }
}
