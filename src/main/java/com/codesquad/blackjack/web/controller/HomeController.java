package com.codesquad.blackjack.web.controller;

import com.codesquad.blackjack.web.service.GameRoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final Logger log = LogManager.getLogger(HomeController.class);

    @Autowired
    private GameRoomService gameRoomService;

    @GetMapping("/")
    public String home(Model model) {
        log.info("home info 여기는 홈입니다.");
        model.addAttribute("games", gameRoomService.findAll());

        return "home";
    }
}
